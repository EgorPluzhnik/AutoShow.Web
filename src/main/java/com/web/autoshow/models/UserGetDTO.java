package com.web.autoshow.models;

import com.web.autoshow.Sex;

public class UserGetDTO {
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final String email;
    private final Sex sex;

    public UserGetDTO(String name, String surname, String phoneNumber, String email, Sex sex) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Sex getSex() {
        return sex;
    }
}


