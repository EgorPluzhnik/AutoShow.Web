package com.web.autoshow.dao;

import com.web.autoshow.models.User;
import com.web.autoshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
  private final UserRepository userRepository;

  @Autowired
  public UserDAO(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void add(User person) {
    userRepository.save(person);
  }

  public User getPerson(Long id) {
    return userRepository.findById(id).get();
  }
}
