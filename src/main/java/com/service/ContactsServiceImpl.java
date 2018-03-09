package com.service;

import com.dto.ContactsDto;
import com.entity.Contact;
import com.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    public ContactsDto createContact(ContactsDto contactsDto) {
        List<Contact> contacts = convertToEntitys(contactsDto);
        contactsRepository.saveAll(contacts);
        return null;
    }

    @Override
    public ContactsDto updateContact(String contactName, ContactsDto contacts) {

        return null;
    }

    @Override
    public ContactsDto getContactByName(String contactName) {
        List<Contact> contacts = contactsRepository.findByName(contactName);
        return convertToDto(contacts);
    }


    private List<Contact> convertToEntitys(ContactsDto contactsDto) {
        List<Contact> contacts = new LinkedList<>();

        for (String phoneNumber : contactsDto.getPhones()) {
            contacts.add(new Contact(contactsDto.getName(), phoneNumber));
        }
        return contacts;
    }

    private ContactsDto convertToDto(List<Contact> contacts) {
        List<String> phones = contacts.stream()
                .map(Contact::getPhoneNumber)
                .collect(Collectors.toList());
        String name = contacts.get(0).getName();
        return new ContactsDto(name, phones);
    }

    //TODO run some tests
    @Override
    public HttpStatus deleteContactByName(String contactName) {
        List<Contact> contacts = contactsRepository.findByName(contactName);
        if (contacts.size() == 0) {
            return HttpStatus.NOT_FOUND;
        } else {
            contactsRepository.deleteAll(contacts);
            return HttpStatus.OK;
        }
    }
}
