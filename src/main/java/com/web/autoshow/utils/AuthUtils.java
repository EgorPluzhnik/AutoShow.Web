package com.web.autoshow.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

@Component
public class AuthUtils {
  public String cipher(long pid) {
    return "abc" + pid + "cba";
  }

  public long decipher(String value) {
    return Long.parseLong(value.replaceAll("abc", "").replaceAll("cba", ""));
  }

  public Cookie findPidCookie(Cookie[] cookies) {
    if (cookies != null) {
      Optional<Cookie> pidCookie = Arrays.stream(cookies)
          .filter(cookie -> cookie.getName().equals("PID"))
          .findAny();
      if (pidCookie.isPresent()) {
        return pidCookie.get();
      }
    }
    return null;
  }
}
