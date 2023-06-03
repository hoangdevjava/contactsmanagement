package com.lab.contactsmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@GetMapping("/contact/{id}")
	public ResponseEntity<Contact> getContactByIdPath(@PathVariable Long id) {
		return ResponseEntity.ok().body(contactService.getContactById(id).get());
	}

	@PostMapping({"/contact", "/contact/{id}"})
	public ResponseEntity<Contact> saveOrUpdateContact(@PathVariable(required = false) Long id,
			@RequestBody @Valid ContactRequestDTO contactRequest) {
			return new ResponseEntity<>(contactService.saveContact(contactRequest, id), HttpStatus.CREATED);
			
	}
	
	@DeleteMapping("/contact/{id}")
	public void deleteContact(@PathVariable Long id) {
		Optional<Contact> contactOpt = contactService.getContactById(id);
		contactService.deleteContact(contactOpt.get().getId());
	}
}
