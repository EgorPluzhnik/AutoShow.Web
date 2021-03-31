package com.web.autoshow.repositories;

import com.web.autoshow.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
