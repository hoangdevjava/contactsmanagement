package com.lab.contactsmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.contactsmanagement.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
