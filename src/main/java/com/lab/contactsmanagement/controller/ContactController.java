package com.lab.contactsmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab.contactsmanagement.dto.ContactRequestDTO;
import com.lab.contactsmanagement.entity.Contact;
import com.lab.contactsmanagement.service.ContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class ContactController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactService contactService;
	

//	@GetMapping("/contact")
//	public ResponseEntity<List<Contact>> getAllContacts() {
//		return ResponseEntity.ok().body(contactService.getContacts());
//	}
	
	@GetMapping("/contact")
	public ResponseEntity<List<Contact>> listFirstPage(String sortDir) {
		LOGGER.info("ContactController | listFirstPage is started");
		
		LOGGER.info("ContactController | listFirstPage | sortDir : " + sortDir);
		
		return listByPage(1, null);
	}
	
	
	/* This API using for basic search and pagination by lastname/firstname but I think the annotation @Query with parameters on H2 database not working correctly .
		* I tried to hard value on method with parameter keyword String(Ex: contactRepository.findByNameContaining("Hoang", pageable)) then the result output expected.
		* But when I using data from RequestParam (ex: contactRepository.findByNameContaining(keyword, pageable)) it's not working.
		* Solution: I will set up driver MySQL database for testing later. I take time when investigate with H2 at the moment. Sorry about that! :).
	 */
	@GetMapping("/contact/page/{pageNum}") 
	public ResponseEntity<List<Contact>> listByPage(@PathVariable(name = "pageNum") int pageNum, 
			@RequestParam("keyword") String keyword
			) {
		
		LOGGER.info("ContactController | Search API is started");
		LOGGER.info("ContactController | keyWordSearch is: " + keyword);
		Page<Contact> page = contactService.listByPage(pageNum, keyword);
		List<Contact> listContact = page.getContent();
		return ResponseEntity.ok().body(listContact);

	}
	
	@GetMapping("/contact/{id}")
	public ResponseEntity<Contact> getContactByIdPath(@PathVariable Long id) {
		return ResponseEntity.ok().body(contactService.getContactById(id));
	}

	@PostMapping({"/contact", "/contact/{id}"})
	public ResponseEntity<Contact> saveOrUpdateContact(@PathVariable(required = false) Long id,
			@RequestBody @Valid ContactRequestDTO contactRequest) {
			return new ResponseEntity<>(contactService.saveContact(contactRequest, id), HttpStatus.CREATED);
			
	}
	
	@DeleteMapping("/contact/{id}")
	public void deleteContact(@PathVariable Long id) {
		Contact contactOpt = contactService.getContactById(id);
		contactService.deleteContact(contactOpt.getId());
	}
}
