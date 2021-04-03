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
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    private final AuthDAO authDAO;
    private final PersonDAO personDAO;

    public AuthController(AuthDAO authDAO, PersonDAO personDAO) {
        this.authDAO = authDAO;
        this.personDAO = personDAO;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthDTO authDTO,
                         HttpServletResponse res,
                         @Autowired AuthUtils authUtils) {
        long pid = authDAO.getPid(authDTO.getLogin(), authDTO.getPassword());
        if (pid != -1) {
            res.addCookie(new Cookie("PID", authUtils.cipher(pid)));
            return "Logged in";
        }
        return "Wrong login or password";
    }

    @PutMapping("/login")
    public List<String> register(@RequestBody PersonAuthDTO paDTO,
                                 HttpServletResponse res,
                                 @Autowired AuthUtils authUtils) {

        Person person = new Person(paDTO.getName(), paDTO.getSurname(), paDTO.getPhoneNumber(),
            paDTO.getEmail(), paDTO.getSex());

        List<String> duplicates = personDAO.findDuplicateFields(person);
        if (duplicates.size() > 0) {
            return duplicates;
        }

        personDAO.add(person);
        Auth auth = new Auth(paDTO.getLogin(), paDTO.getPassword(), personDAO.getPerson(person.getId()));
        authDAO.add(auth);

        res.addCookie(new Cookie("PID",
            authUtils.cipher(authDAO.getPid(paDTO.getLogin(), paDTO.getPassword()))));

        return new ArrayList<>(Collections.singletonList("Registered"));
    }

    @DeleteMapping("/login")
    public String logout(HttpServletResponse res) {
        res.addCookie(new Cookie("PID", ""));
        return "Logged out";
    }

    @GetMapping("/me")
    public String checkPersonAuth(HttpServletRequest req,
                                   @Autowired AuthUtils authUtils) {
        Cookie pidCookie = authUtils.findPidCookie(req.getCookies());
        if (pidCookie != null) {
            if (!pidCookie.getValue().equals("")) {
                long pid = authUtils.decipher(pidCookie.getValue());
                if (authDAO.exists(pid)) {
                    return "Authorized";
                }
            }
        }
        return "Not Authorized";
    }
}
