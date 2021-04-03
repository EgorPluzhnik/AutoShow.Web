package com.web.autoshow.controllers;

import com.web.autoshow.dao.AuthDAO;
import com.web.autoshow.dao.PersonDAO;
import com.web.autoshow.dto.AuthDTO;
import com.web.autoshow.dto.PersonAuthDTO;
import com.web.autoshow.models.Auth;
import com.web.autoshow.models.Person;
import com.web.autoshow.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String register(@RequestBody PersonAuthDTO paDTO,
                         HttpServletResponse res,
                         @Autowired AuthUtils authUtils) {
        try {
            long pid = personDAO.add(new Person(paDTO.getName(), paDTO.getSurname(),
                paDTO.getPhoneNumber(), paDTO.getEmail(), paDTO.getSex()));
            authDAO.add(new Auth(paDTO.getLogin(), paDTO.getPassword(), personDAO.getPerson(pid)));
            res.addCookie(new Cookie("PID",
                authUtils.cipher(authDAO.getPid(paDTO.getLogin(), paDTO.getPassword()))));

            return "Registered";
        } catch (Exception e) {
            return "Some error occurred";
        }

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
