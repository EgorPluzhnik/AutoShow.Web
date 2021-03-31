package com.web.autoshow.repositories;

import com.web.autoshow.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
