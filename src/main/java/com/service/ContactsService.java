package com.service;

import com.dto.ContactsDto;
import com.entity.Contact;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ContactsService {

    List<Contact> getAll();

    ContactsDto createContact(ContactsDto contacts);

    ContactsDto updateContact(String contactName, ContactsDto contacts);

    ContactsDto getContactByName(String contactName);

    HttpStatus deleteContactByName(String contactName);

}
