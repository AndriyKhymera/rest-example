package com.service;

import com.dto.ContactsDto;
import com.entity.Contact;
import com.repository.ContactsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //TODO group by name
    @Override
    public Optional<ContactsDto> getAll() {
        List<Contact> contacts = contactsRepository.findAll();
        return convertToDto(contacts);
    }

    @Override
    public ContactsDto createContact(ContactsDto contactsDto) {
        List<Contact> contacts = convertToEntity(contactsDto);
        log.info("Saving list" + contacts);
        contacts = contactsRepository.saveAll(contacts);
        return convertToDto(contacts).get();
    }

    @Override
    public HttpStatus updateContact(String contactName, ContactsDto contactsDto) {
        List<Contact> currentContacts = contactsRepository.findByName(contactName);
        if (contactsRepository == null) {
            return HttpStatus.NOT_FOUND;
        }

        List<Contact> newContactsList = convertToEntity(contactsDto);

        List<Contact> retainList = currentContacts.stream()
                .filter(newContactsList::contains)
                .collect(Collectors.toList());

        //contacts to add
        newContactsList.removeAll(retainList);

        //contacts to remove
        currentContacts.removeAll(retainList);

        contactsRepository.saveAll(newContactsList);
        contactsRepository.deleteAll(currentContacts);

        return HttpStatus.OK;
    }

    @Override
    public ContactsDto getContactByName(String contactName) {
        List<Contact> contacts = contactsRepository.findByName(contactName);
        return convertToDto(contacts).get();
    }


    //TODO run some tests
    @Override
    public HttpStatus deleteContactByName(String contactName) {
        List<Contact> contacts = contactsRepository.findByName(contactName);
        if (contacts == null) {
            return HttpStatus.NOT_FOUND;
        } else {
            contactsRepository.deleteAll(contacts);
            return HttpStatus.OK;
        }
    }

    private List<Contact> convertToEntity(ContactsDto contactsDto) {
        List<Contact> contacts = new LinkedList<>();

        for (String phoneNumber : contactsDto.getPhones()) {
            contacts.add(new Contact(contactsDto.getName(), phoneNumber));
        }
        return contacts;
    }

    private Optional<ContactsDto> convertToDto(List<Contact> contacts) {
        if (contacts.size() == 0) {
            return Optional.empty();
        }
        List<String> phones = contacts.stream()
                .map(Contact::getPhoneNumber)
                .collect(Collectors.toList());

        String name = contacts.get(0).getName();
        return Optional.of(new ContactsDto(name, phones));
    }
}
