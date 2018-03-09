package com.controller;

import com.dto.ContactsDto;
import com.entity.Contact;
import com.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @PostMapping
    public ContactsDto createContact(@RequestBody ContactsDto contact) {
        return contactsService.createContact(contact);
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactsService.getAll();
    }

    @GetMapping("/{contactName}")
    public ContactsDto getContact(@PathVariable String contactName) {
        return contactsService.getContactByName(contactName);
    }

    @PutMapping("/{contactName}")
    public ContactsDto updateContact(@PathVariable String contactName, @RequestBody ContactsDto contact) {
        return contactsService.updateContact(contactName, contact);
    }

    @DeleteMapping("/{contactName}")
    public ResponseEntity<?> deleteContact(@PathVariable String contactName) {
        HttpStatus status = contactsService.deleteContactByName(contactName);
        return ResponseEntity.status(status).build();
    }
}
