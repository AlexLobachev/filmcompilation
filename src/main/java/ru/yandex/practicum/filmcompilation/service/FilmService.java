package ru.yandex.practicum.filmcompilation.service;

import ru.yandex.practicum.filmcompilation.model.Film;
import ru.yandex.practicum.filmcompilation.model.User;

import java.util.List;
import java.util.Map;

public interface FilmService {
    Map<String, String> addLike(long idFilm, User user);

    Map<String, String> deleteLike(long idFilm, User user);

    List<Film> getFilmByLikes(int count);

}
