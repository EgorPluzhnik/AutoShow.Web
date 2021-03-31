package com.web.autoshow.controllers;

import com.google.gson.Gson;
import com.web.autoshow.dao.PersonDAO;
import com.web.autoshow.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/people")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PeopleController {

  private final PersonDAO personDAO;

  @Autowired
  public PeopleController(PersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  @GetMapping("/{id}")
  public Person add(@PathVariable("id") Long id) {
    return personDAO.getPerson(id);
  }

  @PostMapping()
  public String add(HttpServletRequest request) throws IOException {
    // Чтобы распарсить payload и сразу же создать объект
    Person person = new Gson().fromJson(request.getReader(), Person.class);
    personDAO.add(person);
    return "Done";
  }
}
