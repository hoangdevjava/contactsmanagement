package com.lab.contactsmanagement.service.impl;

import java.util.List;

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
	public Contact saveContact(ContactRequestDTO contactRequest) {
		Contact contact = Contact.builder()
				.name(contactRequest.getName())
				.email(contactRequest.getEmail())
				.telephone(contactRequest.getTelephone())
				.postalAddress(contactRequest.getPostalAddress())
				.build();
		
		return contactRepository.save(contact);
	}

	@Override
	public List<Contact> getContacts() {
		return contactRepository.findAll();
	}

	@Override
	public void saveAllContacts(List<Contact> contacts) {
		contactRepository.saveAll(contacts);
	}

}
