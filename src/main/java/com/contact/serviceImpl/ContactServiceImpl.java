package com.contact.serviceImpl;

import com.contact.Repository.ContactRepo;
import com.contact.entity.Contact;
import com.contact.exception.BusinessException;
import com.contact.exception.EmptyInputException;
import com.contact.payload.ContactRequest;
import com.contact.payload.ContactResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    public ContactRepo contactRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactResponse create(ContactRequest contactRequest) {

        if (contactRequest.getFirstName().isEmpty()) {
            throw new EmptyInputException("601", "Please send proper name, It blank");
        }
        Contact contact = dtoToContact(contactRequest);
        Contact save = contactRepo.save(contact);
        return contactToResponse(save);
    }


    @Override
    public List<ContactResponse> getAllContact() {
        try {
            List<Contact> all = contactRepo.findAll();
            if (all.isEmpty()) {
                throw new BusinessException("604", "Hey List completely Empty");
            }
            return contactToResponse(all);
        } catch (Exception e) {
            throw new BusinessException("605", "Something went wrong in Service layer while saving employee" + e.getMessage());
        }
    }

    @Override
    public ContactResponse updateContactById(ContactRequest contactRequest, Long id) {
        try {
            Contact contactData = contactRepo.findById(id).get();
            contactData.setId(id);
            contactData.setFirstName(contactRequest.getFirstName());
            contactData.setLastName(contactRequest.getLastName());
            contactData.setEmail(contactRequest.getEmail());
            contactData.setPhoneNumber(contactRequest.getPhoneNumber());
            Contact save = contactRepo.save(contactData);
            return contactToResponse(save);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("609", "Given Employee id  is null, plese send some other id " + e.getMessage());
        } catch (java.util.NoSuchElementException e) {
            throw new BusinessException("6010", "Given Employee id doesn't exist in DB" + e.getMessage());
        }
    }

    @Override
    public void deleteContactById(Long id) {
        try {
            contactRepo.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("608", "Given Employee id  is null, plese send some other id " + e.getMessage());
        }
    }

    @Override
    public Page<Contact> search(String firstName, String lastName, String email, Pageable pageable) {
        Page<Contact> all;

        if (!StringUtils.isEmpty(firstName) || !StringUtils.isEmpty(lastName)|| !StringUtils.isEmpty(email)) {
            all = contactRepo.findByFirstNameOrLastNameOrEmail(firstName, lastName, email, pageable);
        }else{
            throw new EmptyInputException("601", "Please send proper name, It blank");
        }

        return all;
    }


    private Contact dtoToContact(ContactRequest contactRequest){
        Contact contact = this.modelMapper.map(contactRequest,Contact.class);
        return contact;
    }
    private ContactRequest ContactToDto(Contact contact){
        ContactRequest contactRequest = this.modelMapper.map(contact,ContactRequest.class);
        return contactRequest;
    }

    private ContactResponse contactToResponse(Contact contact){
        ContactResponse contactResponse = this.modelMapper.map(contact,ContactResponse.class);
        return contactResponse;
    }

    private List<ContactResponse> contactToResponse(List<Contact> contact){
        List<ContactResponse> contactResponse = Collections.singletonList(this.modelMapper.map(contact, ContactResponse.class));
        return contactResponse;
    }

}
