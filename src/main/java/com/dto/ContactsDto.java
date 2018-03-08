package com.dto;

import java.util.List;

public class ContactsDto {

    private String name;
    private List<String> phones;

    public ContactsDto(String name, List<String> phones) {
        this.name = name;
        this.phones = phones;
    }

    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}
