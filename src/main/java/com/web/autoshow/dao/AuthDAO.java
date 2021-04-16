package com.web.autoshow.dao;

import com.web.autoshow.models.Auth;
import com.web.autoshow.repositories.AuthRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthDAO {
    private final AuthRepository authRepository;

    public AuthDAO(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean exists(long pid) {
        return authRepository.existsById(pid);
    }

    // Получить ид по логину и паролю
    public long getPid(String login, String password) {
        List<Auth> auths = new ArrayList<>();
        // Достаем все зарегистрированные аккаунты
        authRepository.findAll().forEach(auths::add);
        for (Auth auth : auths) {
            // Если логин и пароль какого-то пользователя равны тем, что были переданы,
            // то возвращаем его ид.
            if (auth.getLogin().equals(login) && auth.getPassword().equals(password)) {
                return auth.getPid();
            }
        }

        return -1;
    }

    // Добавить зарегистрированный акк в БД.
    public void add(Auth auth) {
        authRepository.save(auth);
    }
}
