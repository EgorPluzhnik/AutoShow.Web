package com.web.autoshow.controllers;

import com.web.autoshow.dao.AuthDAO;
import com.web.autoshow.dao.PersonDAO;
import com.web.autoshow.dto.AuthDTO;
import com.web.autoshow.dto.PersonAuthDTO;
import com.web.autoshow.models.Auth;
import com.web.autoshow.models.Person;
import com.web.autoshow.utils.AuthUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.ErrorManager;

@RestController
@RequestMapping("/auth")
// Разрешаем делать кросс-доменные запросы
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    private final AuthDAO authDAO;
    private final PersonDAO personDAO;

    public AuthController(AuthDAO authDAO, PersonDAO personDAO) {
        this.authDAO = authDAO;
        this.personDAO = personDAO;
    }

    // Произвести аутентификацию.
    @PostMapping("/login")
    public String login(@RequestBody AuthDTO authDTO,
                         HttpServletResponse res,
                         @Autowired AuthUtils authUtils) {
        // Получаем ид человека по логину и паролю
        long pid = authDAO.getPid(authDTO.getLogin(), authDTO.getPassword());
        // Если ид есть в БД, а то есть человек зарегистрирован, то возвращаем куки, куда по ключу PID
        // задаем зашифрованный ид, используя утилиту cipher из authUtils
        // Также возвращаем сообщение, что человек вошёл
        if (pid != -1) {
            res.addCookie(new Cookie("PID", authUtils.cipher(pid)));
            return "Logged in";
        }
        return "Wrong login or password";
    }

    // Создать аккаунт
    @PutMapping("/login")
    public List<String> register(@RequestBody PersonAuthDTO paDTO,
                                 HttpServletResponse res,
                                 @Autowired AuthUtils authUtils) {
        // Создаем нового пользователя по введённым данным в форму
        Person person = new Person(paDTO.getName(), paDTO.getSurname(), paDTO.getPhoneNumber(),
            paDTO.getEmail(), paDTO.getSex());
        // Теперь начинается проверка на то, что пользователя с таким номером и почтой ещё нет.
        List<String> duplicates = personDAO.findDuplicateFields(person);
        // Если всё-таки были найдены дубликаты, то возвращаем сообщения, что были возвращены из
        // метода findDuplicateFields()
        if (duplicates.size() > 0) {
            return duplicates;
        }
        // Иначе добавляем пользователя в БД
        personDAO.add(person);
        // Также добавляем в БД инфу о том, что такой акк зарегистрирован.
        Auth auth = new Auth(paDTO.getLogin(), paDTO.getPassword(), personDAO.getPerson(person.getId()));
        authDAO.add(auth);
        // Возвращаем куки, задавя и шифруя pid.
        res.addCookie(new Cookie("PID",
            authUtils.cipher(authDAO.getPid(paDTO.getLogin(), paDTO.getPassword()))));

        // Тестил коллекции, поэтому заюзал их тут
        // Думаю, возвращать более ничего не нужно. Эндпоинта /me должно хватить.
        return new ArrayList<>(Collections.singletonList("Registered"));
    }

    // Разлогиниться
    @DeleteMapping("/login")
    public String logout(HttpServletResponse res) {
        // Просто обновляем куки с ключом PID, задавая пустое значение.
        res.addCookie(new Cookie("PID", ""));
        return "Logged out";
    }

    // Произвести авторизацию
    @GetMapping("/me")
    public String checkPersonAuth(HttpServletRequest req,
                                   @Autowired AuthUtils authUtils) {
        // Находим саму куку с ключом PID.
        Cookie pidCookie = authUtils.findPidCookie(req.getCookies());

        if (pidCookie != null) {
            // Если такая есть и её значение непустое, то
            if (!pidCookie.getValue().equals("")) {
                // Получаем сам PID, расшифровывая запись из куки, используя утилиту decipher
                long pid = authUtils.decipher(pidCookie.getValue());
                // Проверяем, есть ли в БД зарегистрированный акк по такому ид.
                if (authDAO.exists(pid)) {
                    return "Authorized";
                }
            }
        }
        return "Not Authorized";
    }
}
