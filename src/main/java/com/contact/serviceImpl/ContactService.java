package com.contact.serviceImpl;

import com.contact.entity.Contact;
import com.contact.payload.ContactRequest;
import com.contact.payload.ContactResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ContactService {

    ContactResponse create(ContactRequest contactRequest);

    List<ContactResponse> getAllContact();
    ContactResponse updateContactById(ContactRequest contactRequest, Long id);
    void deleteContactById(Long id);

    Page<Contact> search(String firstName, String lastName, String email, Pageable pageable);

}
