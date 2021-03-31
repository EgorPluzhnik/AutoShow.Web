package com.web.autoshow.controllers;

import com.web.autoshow.common.Sex;
import com.web.autoshow.dao.UserDAO;
import com.web.autoshow.models.User;
import com.web.autoshow.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userDAO.getPerson(id);
    }

    @PostMapping()
    public String addUser(HttpServletRequest request) throws IOException {
        // Чтобы распарсить payload и сразу же создать объект
        User person = new User("123", 22, "123");
        userDAO.add(person);
        return "Done";
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {

    }

    @GetMapping("/profile")
    public UserDTO getUserProfileInfo() {
        return new UserDTO("Егор", "Scala Джонсон", "8921223221", "egor_rock@scala.power", Sex.Male);
    }
}
