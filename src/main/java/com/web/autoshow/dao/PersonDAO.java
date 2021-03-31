package com.web.autoshow.dao;

import com.web.autoshow.models.Person;
import com.web.autoshow.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {
  private final PersonRepository personRepository;

  @Autowired
  public PersonDAO(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public void add(Person person) {
    // TODO
  }

  public Person getPerson(Long id) {
    return personRepository.findById(id).get();
  }
}
