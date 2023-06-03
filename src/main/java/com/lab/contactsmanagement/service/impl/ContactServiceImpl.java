package com.lab.contactsmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.contactsmanagement.dto.ContactRequestDTO;
import com.lab.contactsmanagement.entity.Contact;
import com.lab.contactsmanagement.repository.ContactRepository;
import com.lab.contactsmanagement.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public Contact saveContact(ContactRequestDTO contactRequest, Long contactId) {
		Contact contactBuildData = new Contact();

		if (contactId != null) {
			contactBuildData = getContactById(contactId).get();
			contactBuildData = Contact.builder()
					.Id(contactId).name(contactRequest.getName())
					.email(contactRequest.getEmail())
					.telephone(contactRequest.getTelephone())
					.postalAddress(contactRequest.getPostalAddress())
					.build();
		} else {
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
		return contactRepository.findAll();
	}

	@Override
	public void saveAllContacts(List<Contact> contacts) {
		contactRepository.saveAll(contacts);
	}

	@Override
	public Optional<Contact> getContactById(Long id) {

		Optional<Contact> optContact = contactRepository.findById(id);

		if (optContact.isPresent())
			return optContact;
		throw new RuntimeException("No user by Id: " + id);
	}

	@Override
	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
	}

}
