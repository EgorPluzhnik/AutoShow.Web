package com.web.autoshow.dto;

import com.web.autoshow.common.Sex;

public class PersonAuthDTO {
    private final String login;
    private final String password;
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final String email;
    private final Sex sex;

    public PersonAuthDTO(String name, String surname, String phoneNumber,
                         String email, Sex sex, String password, String login) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.sex = sex;
        this.password = password;
        this.login = login;
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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}


