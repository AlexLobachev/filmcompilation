package ru.yandex.practicum.filmcompilation.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmcompilation.exception.ValidationException;
import ru.yandex.practicum.filmcompilation.service.UserService;
import ru.yandex.practicum.filmcompilation.service.UserServiceImpl;
import ru.yandex.practicum.filmcompilation.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс - котроллер для пользователей
 *
 * @author Алексей Лобачёв
 * @версия 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService = new UserServiceImpl();

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
