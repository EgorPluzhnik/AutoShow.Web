package com.web.autoshow.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {
  @Bean
  public String cipher(long id) {
    return "abc" + String.valueOf(id) + "cba";
  }

  @Bean
  public long decipher(String value) {
    return Long.parseLong(value.replaceAll("abc", ""));
  }
}
