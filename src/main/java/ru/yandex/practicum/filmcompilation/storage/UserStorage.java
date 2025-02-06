package ru.yandex.practicum.filmcompilation.storage;


import ru.yandex.practicum.filmcompilation.model.User;

import java.util.List;

public interface UserStorage {
    User createUser(User user);

    User updateUser(User user);

    List<User> getAllUsers();

    User getUserById(long id);
}
