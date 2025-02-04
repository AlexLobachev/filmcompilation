package ru.yandex.practicum.filmcompilation.service;

import ru.yandex.practicum.filmcompilation.film.Film;

import java.util.List;

public interface FilmService {
    Film createFilm(Film film);

    Film updateFilm(Film film);

    List<Film> getAllFilms();
}
