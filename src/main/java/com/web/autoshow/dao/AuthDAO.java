package com.web.autoshow.dao;

import com.web.autoshow.repositories.AuthRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthDAO {
    private final AuthRepository authRepository;

    public AuthDAO(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean exists(long pid) {
        return authRepository.existsById(pid);
    }
}
