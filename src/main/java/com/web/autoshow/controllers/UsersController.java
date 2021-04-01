//package com.web.autoshow.controllers;
//
//import com.web.autoshow.common.Message;
//import com.web.autoshow.common.Sex;
//import com.web.autoshow.dto.AuthDTO;
//import com.web.autoshow.dto.UserDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Arrays;
//
//@RestController
//@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//public class UsersController {
//
//    @PostMapping("/login")
//    public Message AuthorizeUser(@RequestBody AuthDTO userAuthPostDto,
//                                 @Autowired Message message, // @Autowired: ищет и передает бин Message в метод
//                                 HttpServletRequest req,
//                                 HttpServletResponse res) {
//
//        // Необязательно, оказывается, возвращать респонс в return, чтобы это работало :P
//        res.addCookie(new Cookie("USER-ID", "1"));
//
//        message.setMessage("OK");
//        return message;
//    }
//
//    @GetMapping("/me")
//    public Message Me(HttpServletRequest req, @Autowired Message message) {
//        Cookie[] cookies = req.getCookies();
//        if (cookies != null && Arrays.stream(cookies).anyMatch(cookie -> cookie.getValue().equals("1"))) {
//            message.setMessage("Authorized");
//            return message;
//        }
//        message.setMessage("Not Authorized");
//        return message;
//    }
//
//    @DeleteMapping("/{id}")
//    public void DeleteUser(@PathVariable int id) {
//
//    }
//
//    @GetMapping("/profile/{login}")
//    public UserDTO GetUserProfileInfo(@PathVariable String login, HttpServletRequest req) {
//        Cookie[] cookies = req.getCookies();
//        return new UserDTO("Егор", "Scala Джонсон", "8921223221", "egor_rock@scala.power", Sex.Male);
//    }
//}
