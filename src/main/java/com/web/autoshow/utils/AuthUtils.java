package com.web.autoshow.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {
  public String cipher(long pid) {
    return "abc" + String.valueOf(pid) + "cba";
  }

  public long decipher(String value) {
    return Long.parseLong(value.replaceAll("abc", ""));
  }
}
