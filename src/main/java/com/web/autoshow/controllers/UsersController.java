package com.web.autoshow.controllers;

import com.web.autoshow.Message;
import com.web.autoshow.Sex;
import com.web.autoshow.models.UserGetDTO;
import com.web.autoshow.models.UserPostDTO;
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
public class UsersController {

    @PostMapping("/login")
    public String AuthorizeUser(@RequestBody UserPostDTO userAuthDto,
                                 //@Autowired Message message,
                                 HttpServletRequest req,
                                 HttpServletResponse res) {
        //message.setMessage("OK");
        Cookie cookie = new Cookie("USER-ID", "1");
        res.addCookie(cookie);
        return "Done";
    }

    @GetMapping("/me")
    public Message Me(HttpServletRequest req, @Autowired Message message) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null && Arrays.stream(cookies).anyMatch(cookie -> cookie.getValue().equals("1"))) {
            message.setMessage("Authorized");
            return message;
        }
        message.setMessage("Not Authorized");
        return message;
    }

    @DeleteMapping("/{id}")
    public void DeleteUser(@PathVariable int id) {

    }

    @GetMapping("/profile/{username}")
    public UserGetDTO GetUserProfileInfo(@PathVariable String username, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        // Обращаемся в бд и вычленяем ИД по username (надо и его хранить в БД)
        // Далее из кукичей вычленяем тоже ИД и сравниваем его с тем, который мы получили по username из БД
        // Если true, то возвращаем все нужные для UI данные. Например, хотим показать ему его аву, то вернём img, и прочее говно
        // Если false, то идёт он нахуй, мошенник ёбаный. Возвращаем просто message "Wrong profile", например,
        // И я в реакте сделаю редирект его на главную страницу, а ещё лучше нахуй закрою сайт
        return new UserGetDTO("Егор", "Scala Джонсон", "8921223221", "egor_rock@scala.power", Sex.Male);
    }
}
