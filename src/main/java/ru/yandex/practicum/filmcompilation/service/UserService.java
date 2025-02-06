package ru.yandex.practicum.filmcompilation.service;

import ru.yandex.practicum.filmcompilation.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, String> addFriend(long userId, long friendId);

    Map<String, String> deleteFriend(long userId, long friendId);

    List<User> getAllFriends(long userId);

    List<User> mutualFriendsWithAnotherUser(long userId, long friendId);
}
