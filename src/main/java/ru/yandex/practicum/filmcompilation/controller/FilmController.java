package ru.yandex.practicum.filmcompilation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmcompilation.exception.ErrorResponse;
import ru.yandex.practicum.filmcompilation.exception.IncorrectRequest;
import ru.yandex.practicum.filmcompilation.exception.NotFound;
import ru.yandex.practicum.filmcompilation.model.Film;
import ru.yandex.practicum.filmcompilation.storage.FilmStorage;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс - котроллер для фильмов
 *
 * @author Алексей Лобачёв
 * @версия 1.0
 */
@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmStorage filmStorage;

    @Autowired
    public FilmController(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new IncorrectRequest(String.format("Дата не может быть раньше %s",LocalDate.of(1895, 12, 28)));
        }
        if (film.getDuration()<0){
            throw new IncorrectRequest("Фильм не может иметь отрицательную длительность");
        }
        return filmStorage.createFilm(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        if(filmStorage.getFilmById(film.getId())==null)
            throw new NotFound(String.format("Фильм с ID <%s> не найден",film.getId()));
        return filmStorage.updateFilm(film);
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

}
