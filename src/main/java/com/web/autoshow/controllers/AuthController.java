package com.web.autoshow.controllers;

//import com.web.autoshow.dto.AuthDTO;
import com.web.autoshow.common.Message;
import com.web.autoshow.dao.AuthDAO;
import com.web.autoshow.dto.AuthDTO;
import com.web.autoshow.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AuthController {

    private AuthDAO authDAO;
    private Message message;
    private AuthUtils authUtils;

    @PostMapping("/login")
    public Message login(@RequestBody AuthDTO authDTO, HttpServletResponse res) {
        long pid = authDAO.getPid(authDTO.getLogin(), authDTO.getPassword());
        if (pid != -1) {
            res.addCookie(new Cookie("PID", authUtils.cipher(pid)));
            message.setMessage("Logged in");
            return message;
        }
        message.setMessage("Wrong login or password");
        return message;
    }

    @PutMapping("/login")
    public void register() {
        // Зарегистрировать акк
    }

    @DeleteMapping("/login")
    public void logout() {
        // Выйти
    }

    @GetMapping("/me")
    public Message checkPersonAuth(HttpServletRequest req,
                                @Autowired AuthUtils authUtils,
                                @Autowired Message message) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            String PID_VALUE = Arrays.stream(cookies)
              .filter(cookie -> cookie.getName().equals("PID"))
              .findAny()
              .toString();
            long pid = authUtils.decipher(PID_VALUE);
            if (authDAO.exists(pid)) {
                message.setMessage("Authorized");
                return message;
            }
        }
        message.setMessage("Not Authorized");
        return message;
    }
}
