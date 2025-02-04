package ru.yandex.practicum.filmcompilation.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmcompilation.film.Film;
import ru.yandex.practicum.filmcompilation.service.FilmService;
import ru.yandex.practicum.filmcompilation.service.FilmServiceImpl;

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
    FilmService filmService = new FilmServiceImpl();

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        return filmService.createFilm(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        return filmService.updateFilm(film);
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }
}
