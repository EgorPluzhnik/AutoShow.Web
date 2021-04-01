package com.web.autoshow.dto;

import com.web.autoshow.models.Person;

public class AuthDTO {
    private final Person person;
    private final String login;
    private final String password;

    public AuthDTO(String login, String password, Person person) {
        this.login = login;
        this.password = password;
        this.person = person;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Person getPerson() {
      return person;
    }
}
