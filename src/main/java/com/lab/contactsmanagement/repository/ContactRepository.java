package com.lab.contactsmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lab.contactsmanagement.entity.Contact;

@Repository
public interface ContactRepository extends SearchRepository<Contact, Long> {
	
	@Query(value = "SELECT * FROM Contact WHERE name LIKE %:key_word%",
			nativeQuery = true)
	public Page<Contact> findAll(@Param("key_word") String keyword, Pageable pageble);
	
	@Query(value = "SELECT * FROM Contact WHERE name LIKE %?1%",
			nativeQuery = true)
	public Page<Contact> findByNameContaining(String keyword, Pageable pageble);

	public Long countById(Long id);
	
	

}
