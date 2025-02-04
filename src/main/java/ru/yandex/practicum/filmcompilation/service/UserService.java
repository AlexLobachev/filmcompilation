package ru.yandex.practicum.filmcompilation.service;


import ru.yandex.practicum.filmcompilation.user.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User updateUser(User user);

    List<User> getAllUsers();
}
