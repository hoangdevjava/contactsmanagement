package com.lab.contactsmanagement.contact;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.lab.contactsmanagement.dto.ContactRequestDTO;
import com.lab.contactsmanagement.entity.Contact;
import com.lab.contactsmanagement.repository.ContactRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ContactRepositoryTests {
	private ContactRepository repo;
	private TestEntityManager entityManager;
	
	@Autowired
	public ContactRepositoryTests(ContactRepository repo, TestEntityManager entityManager) {
		super();
		this.repo = repo;
		this.entityManager = entityManager;
	}
	
	@BeforeEach                                         
    void setUp() {
		List<Contact> contacts = Arrays.asList(
				Contact.builder().name("Nguyen Anh Hoang").email("hoang@gmail.com").telephone("1234567890")
						.postalAddress("+84").build(),
				Contact.builder().name("Nguyen Thi Huong").email("huong@gmail.com").telephone("5678456780")
						.postalAddress("+84").build(),
				Contact.builder().name("Pham Ngoc Quynh").email("quynh@gmail.com").telephone("4564567890")
						.postalAddress("+84").build(),
				Contact.builder().name("Nguyen Van A").email("a@gmail.com").telephone("4242424277")
						.postalAddress("+84").build(),
				Contact.builder().name("Nguyen Van B").email("b@gmail.com").telephone("4564567890")
						.postalAddress("+84").build(),
				Contact.builder().name("Nguyen Van C").email("c@gmail.com").telephone("6343434344")
						.postalAddress("+84").build(),
				Contact.builder().name("Nguyen Van D").email("d@gmail.com").telephone("0545567890")
						.postalAddress("+84").build());

		repo.saveAll(contacts);
    }
	
	

	@Test
	public void testCreateNewContact() {
		
		ContactRequestDTO dto = new ContactRequestDTO(0,"Nguyen Anh Hoang", "hoangga@gmail.com", "0943573498", "+84");
		
		
		Contact contactBuildData = new Contact();
		contactBuildData =	Contact.builder().name(dto.getName())
				.email(dto.getEmail())
				.telephone(dto.getTelephone())
				.postalAddress(dto.getPostalAddress())
				.build();
		
		Contact savedContact = repo.save(contactBuildData);
		
		assertThat(savedContact.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testUpdateContact() {
		long id = 1;
		Contact contactUpdate = repo.findById(id).get();
		contactUpdate.setName("Nguyen Van Test");
		
		repo.save(contactUpdate);
	}
	
	@Test
	public void testListAllContact() {
		
		Iterable<Contact> listContacts = repo.findAll();
		listContacts.forEach(contact -> System.out.println(contact));
	}
	
	@Test
	public void testGetContactById() {
		long id = 1;
		Contact contactById = repo.findById(id).get();
		System.out.println(contactById);
		assertThat(contactById).isNotNull();
	}
	
	@Test
	public void testDeleteContact() {
		long userDeleteContact = 2;
		repo.deleteById(userDeleteContact);
		
	}
	
	@Test
	public void testListFirstPage() {
		int pageNumber = 0;
		int pageSize = 4;

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Contact> page = repo.findAll(pageable);

		List<Contact> listContacts = page.getContent();

		listContacts.forEach(contact -> System.out.println(contact));

		assertThat(listContacts.size()).isEqualTo(pageSize);
	}
}
