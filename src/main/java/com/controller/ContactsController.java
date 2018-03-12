package com.controller;

import com.dto.ContactsDto;
import com.service.ContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    Logger log = LoggerFactory.getLogger(ContactsController.class);

    @PostMapping
    public ContactsDto createContact(@RequestBody ContactsDto contact) {
        return contactsService.createContact(contact);
    }

    @GetMapping
    public ResponseEntity<?> getAllContacts() {
        List<ContactsDto> contactsDto = contactsService.getAll();
//        if (contactsDto.isPresent()) {
//            return ResponseEntity.ok(contactsDto.get());
//        } else return ResponseEntity.ok("No contacts found");
        return ResponseEntity.ok(contactsDto);
    }

    @GetMapping("/{contactName}")
    public ResponseEntity getContact(@PathVariable String contactName) {
        Optional<ContactsDto> contactsDto = contactsService.getContactByName(contactName);
        if (contactsDto.isPresent()) {
            return ResponseEntity.ok(contactsDto.get());
        } else return ResponseEntity.ok("No contacts found");
    }

    @PutMapping("/{contactName}")
    public ResponseEntity updateContact(@PathVariable String contactName, @RequestBody ContactsDto contact) {
        HttpStatus status = contactsService.updateContact(contactName, contact);
        return ResponseEntity.status(status).build();
    }

    @DeleteMapping("/{contactName}")
    public ResponseEntity deleteContact(@PathVariable String contactName) {
        HttpStatus status = contactsService.deleteContactByName(contactName);
        return ResponseEntity.status(status).build();
    }
}
