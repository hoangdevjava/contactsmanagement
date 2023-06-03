package com.lab.contactsmanagement.service;

import java.util.List;
import java.util.Optional;

import com.lab.contactsmanagement.dto.ContactRequestDTO;
import com.lab.contactsmanagement.entity.Contact;


public interface ContactService {
	public Contact saveContact(ContactRequestDTO contactRequest, Long contactId);

	public List<Contact> getContacts();

	public void saveAllContacts(List<Contact> contacts);

	public Optional<Contact> getContactById(Long id);

	public void deleteContact(Long id);
}
