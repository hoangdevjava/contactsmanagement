package com.lab.contactsmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.contactsmanagement.dto.ContactRequestDTO;
import com.lab.contactsmanagement.entity.Contact;
import com.lab.contactsmanagement.service.ContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/contact")
	public ResponseEntity<List<Contact>> getAllContacts() {
		return ResponseEntity.ok().body(contactService.getContacts());
	}

	@PostMapping(value = { "/contact", "/contact/{id}" })
	public ResponseEntity<Contact> saveOrUpdateContact(@PathVariable(required = false) Long contactId,
			@RequestBody @Valid ContactRequestDTO contactRequest) {
		if (contactId == null) 
			return new ResponseEntity<>(contactService.saveContact(contactRequest), HttpStatus.CREATED);
		else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
			
	}

}
