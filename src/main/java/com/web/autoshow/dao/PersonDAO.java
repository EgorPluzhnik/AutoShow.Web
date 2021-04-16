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
    // Результаты-сообщения
    List<String> result = new ArrayList<>();

    try {
      personRepository.findAll().forEach(p -> {
        // Если результатов два, то выходим из forEach
        // (знаю, костыльно, но это самое быстрое решение, что у меня было)
        if (result.size() == 2) throw new EmptyStackException();

        // Если такая почта или номер телефона уже есть, то в результаты добавляется сообщение
        if (p.getEmail().equals(person.getEmail()))
          result.add("This email is already used");

        if (p.getPhoneNumber().equals(person.getPhoneNumber()))
          result.add("This phone number is already used");
      });
    } catch (EmptyStackException e) {
      // Чтобы завершить forEach.
    }

    return result;
  }
}
