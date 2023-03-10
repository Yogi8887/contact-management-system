package com.contact.Repository;

import com.contact.entity.Contact;
import com.contact.payload.ContactResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long> {
    Optional<Contact> findById(Long id);

    Page<Contact> findByFirstNameOrLastNameOrEmail(String firstName, String lastName, String email, Pageable pageable);

}
