package com.web.autoshow.controllers;

import com.web.autoshow.common.Message;
import com.web.autoshow.common.Sex;
import com.web.autoshow.dto.UserAuthPostDTO;
import com.web.autoshow.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UsersController {

    // Имеем две БД
    // 1 - Auth, хранящая логин и пароль и собственный первичный ключ (pk) и pk user
    // 2 - User: name, phone, email, etc... и свой pk тоже

    @PostMapping("/login")
    public Message AuthorizeUser(@RequestBody UserAuthPostDTO userAuthPostDto,
                                 @Autowired Message message, // @Autowired: ищет и передает бин Message в метод
                                 HttpServletRequest req,
                                 HttpServletResponse res) {

        // Необязательно, оказывается, возвращать респонс в return, чтобы это работало :P
        res.addCookie(new Cookie("USER-ID", "1"));

        // Новый акк:
        // Генерируем запись в таблице User
        // Генерируем запись в таблице Auth: pk (наш UID будущий), login, password, userId (primary key)
        // Получаем этот самый UID сгенерированной записи и ставим кукан, а потом можно и сесть на него. Танкисты же
        // А ещё мы кроссфитеры

        // Уже существующий акк (просто вход)
        // По userAuthPostDto.login ищем запись в Auth. Сохраняем UID.
        // В этой же записи по userId переходим на юзера и возвращаем мне все нужные данные о нём. Я рисую, затем, UI.
        // Также, не забываем поставить этот UID в куки.

        message.setMessage("OK");
        return message;
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

        // В кукичах приходит UID, это pk (auth). По нему мы узнаем, есть ли хоть одна запись в БД с таким uid
        // Да - красава, ты наш, ты танкист. return пробитие.exe
        // Нет? Возвращаем "Not Authorized". И я делаю UI под это.
    }

    @DeleteMapping("/{id}")
    public void DeleteUser(@PathVariable int id) {

    }

    @GetMapping("/profile/{login}")
    public UserDTO GetUserProfileInfo(@PathVariable String login, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        // Обращаемся в БД и вычленяем ИД по login из таблицы auth, хранящая в себе pk, login, name, pk таблицы user
        // Далее из кукичей получаем тоже ИД и сравниваем его с тем, который мы получили по username из БД
        // Если true, то возвращаем все нужные для UI данные. Например, хотим показать ему его аву, то вернём img, и прочее говно
        // Если false, то идёт он нахуй, мошенник ёбаный. Возвращаем просто message "Wrong profile", например,
        // И я в реакте сделаю редирект его на главную страницу, а ещё лучше нахуй закрою сайт
        return new UserDTO("Егор", "Scala Джонсон", "8921223221", "egor_rock@scala.power", Sex.Male);
    }
}
