package com.lab.contactsmanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lab.contactsmanagement.dto.ContactRequestDTO;
import com.lab.contactsmanagement.entity.Contact;
import com.lab.contactsmanagement.exceptions.ContactNotFoundException;


public interface ContactService {
	public Contact saveContact(ContactRequestDTO contactRequest, Long contactId);

	public void saveAllContacts(List<Contact> contacts);

	public Contact getContactById(Long id) throws ContactNotFoundException;

	public void deleteContact(Long id);
	
	public Page<Contact> listByPage(int pageNum, String keyword);
}
	