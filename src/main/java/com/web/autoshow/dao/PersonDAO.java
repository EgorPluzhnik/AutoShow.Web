package com.web.autoshow.dao;

import com.web.autoshow.models.Person;
import com.web.autoshow.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {
  private final PersonRepository personRepository;

  public PersonDAO(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public long add(Person person) {
    return personRepository.save(person).getId();
  }

  public Person getPerson(Long id) {
    return personRepository.findById(id).get();
  }
}
