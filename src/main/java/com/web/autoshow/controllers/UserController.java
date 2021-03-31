package com.web.autoshow.controllers;

import com.google.gson.Gson;
import com.web.autoshow.dao.UserDAO;
import com.web.autoshow.models.User;
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
  public User add(@PathVariable("id") Long id) {

    return userDAO.getPerson(id);
  }

  @PostMapping()
  public String add(HttpServletRequest request) throws IOException {
    // Чтобы распарсить payload и сразу же создать объект
    User person = new User("123", 22, "123");

    userDAO.add(person);
    return "Done";
  }
}
