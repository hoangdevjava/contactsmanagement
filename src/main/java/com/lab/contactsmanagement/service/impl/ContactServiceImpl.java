package com.lab.contactsmanagement.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lab.contactsmanagement.controller.ContactController;
import com.lab.contactsmanagement.dto.ContactRequestDTO;
import com.lab.contactsmanagement.entity.Contact;
import com.lab.contactsmanagement.exceptions.ContactNotFoundException;
import com.lab.contactsmanagement.repository.ContactRepository;
import com.lab.contactsmanagement.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
	public static final int ROOT_CONTACT_PER_PAGE = 5;
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public Contact saveContact(ContactRequestDTO contactRequest, Long contactId) {
		Contact contactBuildData = new Contact();

		if (contactId != null) {
			//Create Contact
			contactBuildData = getContactById(contactId);
			contactBuildData = Contact.builder()
					.Id(contactId).name(contactRequest.getName())
					.email(contactRequest.getEmail())
					.telephone(contactRequest.getTelephone())
					.postalAddress(contactRequest.getPostalAddress())
					.build();
		} else {
			//Update Contact
			contactBuildData = Contact.builder().name(contactRequest.getName())
					.email(contactRequest.getEmail())
					.telephone(contactRequest.getTelephone())
					.postalAddress(contactRequest.getPostalAddress())
					.build();

		}

		return contactRepository.save(contactBuildData);
	}

	@Override
	public List<Contact> getContacts() {
		return null;
	}

	@Override
	public void saveAllContacts(List<Contact> contacts) {
		contactRepository.saveAll(contacts);
	}

	@Override
	public Contact getContactById(Long id) throws ContactNotFoundException {
		try {
			return contactRepository.findById(id).get();
		} catch (NoSuchElementException  ex) {
			LOGGER.error("Could not find any category with ID: " + ex.getMessage());
			throw new ContactNotFoundException("Could not find any category with ID " + id);
		}
	}

	@Override
	public void deleteContact(Long id) {
		Long countById = contactRepository.countById(id);
		
		if (countById == null || countById == 0) {
			LOGGER.error("Could not find any contact with ID " + id);
			throw new RuntimeException("Could not find any contact with ID " + id);
		}
		
		contactRepository.deleteById(id);
	}

	@Override
	public Page<Contact> listByPage(int pageNum, String keyword) {

		Pageable pageable = PageRequest.of(pageNum - 1, ROOT_CONTACT_PER_PAGE);

		if (keyword != null) {
			return contactRepository.findAll(keyword, pageable);
		}

		return contactRepository.findAll(pageable);		
	}

}
