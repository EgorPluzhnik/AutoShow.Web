package com.web.autoshow.controllers;

import com.web.autoshow.Sex;
import com.web.autoshow.models.UserGetDTO;
import com.web.autoshow.models.UserPostDTO;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UsersController {

    @PostMapping("/login")
    public void AuthorizeUser(@RequestBody UserPostDTO userAuthDto) {

    }

    @DeleteMapping("/{id}")
    public void DeleteUser(@PathVariable int id) {

    }

    @GetMapping("/profile")
    public UserGetDTO GetUserProfileInfo() {
        return new UserGetDTO("Егор", "Scala Джонсон", "8921223221", "egor_rock@scala.power", Sex.Male);
    }
}
