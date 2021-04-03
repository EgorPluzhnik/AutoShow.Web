package com.web.autoshow.dao;

import com.web.autoshow.models.Person;
import com.web.autoshow.repositories.PersonRepository;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

@Component
public class PersonDAO {
  private final PersonRepository personRepository;

  public PersonDAO(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public void add(Person person) {
    personRepository.save(person);
  }

  public Person getPerson(Long id) {
    return personRepository.findById(id).get();
  }

  public List<String> findDuplicateFields(Person person) {
    List<String> result = new ArrayList<>();

    try {
      personRepository.findAll().forEach(p -> {
        if (result.size() == 2) throw new EmptyStackException();

        if (p.getEmail().equals(person.getEmail()))
          result.add("This email is already used");

        if (p.getPhoneNumber().equals(person.getPhoneNumber()))
          result.add("This phone number is already used");
      });
    } catch (EmptyStackException e) {
      // To end forEach
    }

    return result;
  }
}
