package ru.yandex.practicum.filmcompilation.storage;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmcompilation.model.User;

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
@Component
public class UserStorageImpl implements UserStorage {
    private long id = 0;
    private final Map<Long, User> userMap = new HashMap<>();

    @Override
    public User createUser(User user) {
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        user.setId(generationId());
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        if (!userMap.containsKey(user.getId())) {
            throw new ValidationException(String.format("Пользователь c ID <%s> не найден", user.getId()));
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
