package com.lab.contactsmanagement.contact;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.contactsmanagement.ContactsmanagementApplication;
import com.lab.contactsmanagement.controller.ContactController;
import com.lab.contactsmanagement.dto.ContactRequestDTO;
import com.lab.contactsmanagement.entity.Contact;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContactsmanagementApplication.class)
@AutoConfigureMockMvc
public class ContactControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ContactController contactController;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@BeforeEach
    public void init() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }
	
	@Test
	public void getAllContactAPI() throws Exception 
	{
	  mvc.perform(get("/api/contact")
	  			.accept(APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk());
	}

	@Test
	public void testGetContact() throws Exception {
		long id = 1;
		ResponseEntity<Contact> contact = getContact();
		given(contactController.getContactByIdPath(id)).willReturn(contact);
		mvc.perform(get("/api/contact/{id}", 1).contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("name", is(contact.getBody().getName())));
	}

	@Test
	public void testAddContact() throws Exception {
		ContactRequestDTO dto = ContactRequestDTO.builder().name("Nguyen Van A")
				.email("NguyenVanA@gmail.com")
				.telephone("0445754275")
				.postalAddress("+84")
				.build();
		
		mvc.perform(post("/api/contact").content(asJson(dto)).contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void updateContactAPI() throws Exception 
	{
		ContactRequestDTO dto = ContactRequestDTO.builder().name("Nguyen Van A")
				.id(1)
				.email("NguyenVanA@gmail.com")
				.telephone("0445754275")
				.postalAddress("+84")
				.build();
	  
	  mvc.perform(post("/api/contact/{id}", 1).content(asJson(dto)).contentType(APPLICATION_JSON))
      .andExpect(status().isOk()).andReturn();
	}
	
	@Test
	   public void testDeleteContact() throws Exception {
	      mvc.perform(delete("/api/contact/{id}", 1).contentType(APPLICATION_JSON)).andExpect(status().isOk())
	      .andExpect(status().isOk()).andReturn();
	   }

	private ResponseEntity<Contact> getContact() {
		Contact contact = Contact.builder().name("Nguyen Van A")
				.email("NguyenVanA@gmail.com")
				.telephone("0445754275")
				.postalAddress("+84")
				.build();
		return ResponseEntity.ok().body(contact);
	}

	private static String asJson(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
