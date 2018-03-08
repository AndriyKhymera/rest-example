package com.service;

import com.dto.ContactsDto;
import com.entity.Contact;
import com.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

    //TODO group by name
    @Override
    public List<Contact> getAll() {
        return contactsRepository.getAll();
    }

    @Override
    public ContactsDto createContact(ContactsDto contacts) {
        return contactsRepository.save();
    }

    @Override
    public ContactsDto updateContact(String contactName, ContactsDto contacts) {
        return null;
    }

    @Override
    public ContactsDto getContactByName(String contactName) {
        return null;
    }
    
}
