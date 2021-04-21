package com.web.autoshow.repositories;

import com.web.autoshow.models.Person;
import com.web.autoshow.models.Show;
import org.springframework.data.repository.CrudRepository;

public interface ShowRepository extends CrudRepository<Show, Long> {
}
