package com.lab.contactsmanagement.service;

import java.util.List;

import com.lab.contactsmanagement.dto.ContactRequestDTO;
import com.lab.contactsmanagement.entity.Contact;


public interface ContactService {
	public Contact saveContact(ContactRequestDTO contactRequest);

	public List<Contact> getContacts();

	public void saveAllContacts(List<Contact> contacts);
}
