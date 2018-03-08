package com.controller;

import com.dto.ContactsDto;
import com.entity.Contact;
import com.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{name}")
    public ContactsDto getContact(@PathVariable String name) {
        return contactsService.getContactByName(name);
    }

    @PutMapping("/{name}")
    public ContactsDto updateContact(@PathVariable String contactName, @RequestBody ContactsDto contact) {
        return contactsService.updateContact(contactName, contact);
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactsService.getAll();
    }

}
