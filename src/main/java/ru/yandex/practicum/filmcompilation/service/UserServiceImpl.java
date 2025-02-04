package ru.yandex.practicum.filmcompilation.service;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmcompilation.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Бизнес логика пользователей(П)
 *
 * @author Алексей Лобачёв
 * @версия 1.0
 */
@Slf4j
public class UserServiceImpl implements UserService {
    private long id = 0;
    private final Map<Long, User> userMap = new HashMap<>();

    @Override
    public User createUser(User user) {
        if (user.getName()==null){
            user.setName(user.getLogin());
        }

        user.setId(generationId());
        log.debug("ID изменился на {} ",user.getId());
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (user.getName()==null){
            user.setName(user.getLogin());
        }
        if (!userMap.containsKey(user.getId())){
            throw new ValidationException("Пользователь не найден");
        }
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    private long generationId() {
        id++;
        return id;
    }
}
