package com.web.autoshow.controllers;

import com.web.autoshow.dto.UserAuthPostDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    @PostMapping("/login")
    public void authorizeUser(@RequestBody UserAuthPostDTO userAuthDto) {
        // Проверка аутентификации
    }

    @GetMapping("/me")
    public void getAuthUserInfo() {

    }
}
