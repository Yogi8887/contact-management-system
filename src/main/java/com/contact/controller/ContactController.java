package com.contact.controller;

import com.contact.Repository.ContactRepo;
import com.contact.entity.Contact;
import com.contact.payload.ContactRequest;
import com.contact.payload.ContactResponse;
import com.contact.serviceImpl.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService contactService;
    @Autowired
    private ContactRepo contactRepository;

    @Operation(summary = "This is create new contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Create new Record",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "UnAuthorized",
                    content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ContactRequest contactRequest) {
        ContactResponse contactResponse = contactService.create(contactRequest);
        return new ResponseEntity<ContactResponse>(contactResponse, HttpStatus.OK);
    }

    @Operation(summary = "This is get all contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get Record",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content)
    })
    @GetMapping("/read")
    public ResponseEntity<List<ContactResponse>> getAllContact() {
        List<ContactResponse> allContact = contactService.getAllContact();
        return new ResponseEntity<List<ContactResponse>>(allContact, HttpStatus.OK);
    }


    @Operation(summary = "This is Update the Contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update Record",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content)
    })
    @PutMapping("update/{id}")
    public ResponseEntity<ContactResponse> update(@RequestBody ContactRequest contactRequest, @PathVariable Long id) {
        ContactResponse contactById = contactService.updateContactById(contactRequest, id);
        return new ResponseEntity<ContactResponse>(contactById, HttpStatus.OK);
    }

    @Operation(summary = "This is Delete Contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete Record",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content)
    })
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {

        contactService.deleteContactById(id);
    }

    @Operation(summary = "This is Search Contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "SearchRecord",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content)
    })
    @GetMapping("/search")
    public ResponseEntity<Page<Contact>> getAllContact(@RequestParam(value = "firstName", required = false) String firstName,
                                                       @RequestParam(value = "lastName", required = false) String lastName,
                                                       @RequestParam(value = "email", required = false) String email,
                                                       @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<Contact> allContact = contactService.search(firstName, lastName, email, paging);
        return new ResponseEntity<Page<Contact>>(allContact, HttpStatus.OK);
    }


}
