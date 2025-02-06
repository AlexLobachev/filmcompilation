package ru.yandex.practicum.filmcompilation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmcompilation.model.User;
import ru.yandex.practicum.filmcompilation.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Бизнес логика пользователей(П)
 *
 * @author Алексей Лобачёв
 * @версия 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserServiceImpl(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public Map<String, String> addFriend(long userId, long friendId) {

        userStorage.getUserById(userId).getIdFriends().add(friendId);
        userStorage.getUserById(friendId).getIdFriends().add(userId);
        return Map.of(String.format("У пользователя <%s> ", userStorage.getUserById(userId).getName()),
                String.format(" новый друг <%s> ", userStorage.getUserById(friendId).getName()));
    }

    @Override
    public Map<String, String> deleteFriend(long userId, long friendId) {

        userStorage.getUserById(userId).getIdFriends().remove(friendId);
        userStorage.getUserById(friendId).getIdFriends().remove(userId);
        return Map.of(String.format("%s вы удалили ", userStorage.getUserById(userId).getName()),
                String.format(" %s из списка своих друзей", userStorage.getUserById(friendId).getName()));
    }

    @Override
    public List<User> getAllFriends(long userId) {
        List<User> users = new ArrayList<>();
        userStorage.getUserById(userId).getIdFriends().forEach(n -> users.add(userStorage.getUserById(n)));
        return users;
    }

    @Override
    public List<User> mutualFriendsWithAnotherUser(long userId, long friendId) {
        List<User> mutualFriends = new ArrayList<>();
        for (long id : userStorage.getUserById(friendId).getIdFriends()) {
            if (userStorage.getUserById(userId).getIdFriends().contains(id))
                mutualFriends.add(userStorage.getUserById(id));
        }
        return mutualFriends;
    }
}
