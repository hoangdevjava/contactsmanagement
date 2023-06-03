package com.lab.contactsmanagement.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lab.contactsmanagement.entity.Contact;
import com.lab.contactsmanagement.service.ContactService;

@Component
public class ContactDataExcutor implements CommandLineRunner {

	@Autowired
	private ContactService contactService;

	@Override
	public void run(String... args) throws Exception {
		List<Contact> contacts = Arrays.asList(
				Contact.builder().name("Hoang").email("hoang@gmail.com").telephone("1234567890").postalAddress("+84")
						.build(),
				Contact.builder().name("Huong").email("huong@gmail.com").telephone("5678456780").postalAddress("+84")
						.build(),
				Contact.builder().name("Quynh").email("quynh@gmail.com").telephone("4564567890").postalAddress("+84")
						.build());

		contactService.saveAllContacts(contacts);
	}

}
