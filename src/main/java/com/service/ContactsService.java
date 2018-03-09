package com.service;

import com.dto.ContactsDto;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface ContactsService {

    Optional<ContactsDto> getAll();

    ContactsDto createContact(ContactsDto contacts);

    HttpStatus updateContact(String contactName, ContactsDto contacts);

    ContactsDto getContactByName(String contactName);

    HttpStatus deleteContactByName(String contactName);

}
