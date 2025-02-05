package ru.yandex.practicum.filmcompilation.storage;

import ru.yandex.practicum.filmcompilation.model.Film;

import java.util.List;

public interface FilmStorage {
    Film createFilm(Film film);

    Film updateFilm(Film film);

    List<Film> getAllFilms();

    Film getFilmById(long filmId);
}
