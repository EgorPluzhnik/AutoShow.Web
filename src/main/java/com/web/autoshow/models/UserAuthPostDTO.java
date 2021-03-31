package com.web.autoshow.models;

public class UserAuthPostDTO {
    private final String login;
    private final String password;

    public UserAuthPostDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
