package ru.yandex.practicum.filmcompilation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmcompilation.exception.IncorrectRequest;
import ru.yandex.practicum.filmcompilation.exception.NotFound;
import ru.yandex.practicum.filmcompilation.model.User;
import ru.yandex.practicum.filmcompilation.service.UserService;
import ru.yandex.practicum.filmcompilation.storage.UserStorage;

import java.util.List;
import java.util.Map;

/**
 * Класс - котроллер для пользователей
 *
 * @author Алексей Лобачёв
 * @версия 1.0
 */
@RestController
@RequestMapping("/users")
@Component
public class UserController {
    private final UserStorage userStorage;
    private final UserService userService;

    @Autowired
    private UserController(UserStorage userStorage, UserService userService) {
        this.userStorage = userStorage;
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userStorage.createUser(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        getUserById(user.getId());
        return userStorage.updateUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        if (userStorage.getUserById(id) == null)
            throw new NotFound(String.format("Пользователь c ID <%s> не найден", id));
        return userStorage.getUserById(id);
    }

    @PutMapping("/{userId}/friends/{friendId}")
    public Map<String, String> addFriend(@PathVariable long userId, @PathVariable long friendId) {
        if (userId == friendId) {
            throw new IncorrectRequest(String.format("Добавление в друзья самого себя <%s> = <%s>", userId, friendId));
        }
        getUserById(friendId);
        return userService.addFriend(userId, friendId);
    }

    @DeleteMapping("/{userId}/friends/{friendId}")
    public Map<String, String> deleteFriend(@PathVariable long userId, @PathVariable long friendId) {
        if (userId == friendId) {
            throw new IncorrectRequest(String.format("Удаление из друзей самого себя <%s> = <%s>", userId, friendId));
        }
        getUserById(friendId);
        getUserById(userId);
        return userService.deleteFriend(userId, friendId);
    }

    @GetMapping("/{userId}/friends")
    public List<User> getAllFriends(@PathVariable long userId) {
        getUserById(userId);
        return userService.getAllFriends(userId);
    }

    @GetMapping("{userId}/friends/common/{friendId}")
    public List<User> mutualFriendsWithAnotherUser(@PathVariable long userId, @PathVariable long friendId) {
        return userService.mutualFriendsWithAnotherUser(userId, friendId);
    }
}
