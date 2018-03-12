package com.service;

import com.dto.ContactsDto;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface ContactsService {

    List<ContactsDto> getAll();

    ContactsDto createContact(ContactsDto contacts);

    HttpStatus updateContact(String contactName, ContactsDto contacts);

    Optional<ContactsDto> getContactByName(String contactName);

    HttpStatus deleteContactByName(String contactName);

}
