package com.web.autoshow.dto;

import com.web.autoshow.common.Sex;

public class UserDTO {
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final String email;
    private final Sex sex;

    public UserDTO(String name, String surname, String phoneNumber, String email, Sex sex) {
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


