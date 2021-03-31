package com.web.autoshow.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component // Делаем бин из Message
@Scope("prototype")
// Scope("prototype") означает, что будет создаваться новый бин при каждой передаче его в методы.
// Scope("singleton") (по умолчанию) - всего один раз
public class Message {
  private String message;

  public Message(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
