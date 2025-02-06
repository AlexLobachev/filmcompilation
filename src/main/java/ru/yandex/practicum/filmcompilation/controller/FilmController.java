package ru.yandex.practicum.filmcompilation.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmcompilation.exception.IncorrectRequest;
import ru.yandex.practicum.filmcompilation.exception.NotFound;
import ru.yandex.practicum.filmcompilation.model.Film;
import ru.yandex.practicum.filmcompilation.model.User;
import ru.yandex.practicum.filmcompilation.service.FilmService;
import ru.yandex.practicum.filmcompilation.storage.FilmStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Класс - котроллер для фильмов
 *
 * @author Алексей Лобачёв
 * @версия 1.0
 */
@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final FilmStorage filmStorage;
    private final FilmService filmService;
    private final UserController userController;

    @Autowired
    public FilmController(FilmStorage filmStorage, FilmService filmService, UserController userController) {
        this.filmStorage = filmStorage;
        this.filmService = filmService;
        this.userController = userController;
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new IncorrectRequest(String.format("Дата не может быть раньше %s", LocalDate.of(1895, 12, 28)));
        }
        if (film.getDuration() < 0) {
            throw new IncorrectRequest("Фильм не может иметь отрицательную длительность");
        }
        return filmStorage.createFilm(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        getFilmById(film.getId());
        return filmStorage.updateFilm(film);
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable long id) {
        if (filmStorage.getFilmById(id) == null)
            throw new NotFound(String.format("Фильм с ID <%s> не найден", id));
        return filmStorage.getFilmById(id);
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    @PutMapping("/{id}/like/{userId}")
    public Map<String, String> addLike(@PathVariable("id") long idFilm, @PathVariable long userId) {
        getFilmById(idFilm);
        User user = userController.getUserById(userId);
        return filmService.addLike(idFilm, user);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public Map<String, String> deleteLike(@PathVariable("id") long idFilm, @PathVariable long userId) {
        User user = userController.getUserById(userId);
        if (!getFilmById(idFilm).getLike().contains(user)) {
            throw new NotFound(String.format("Пользователь <%s> не ставил лайков фильму <%s>",
                    user.getName(), getFilmById(idFilm).getName()));
        }

        return filmService.deleteLike(idFilm, user);
    }

    @GetMapping("/popular")
    public List<Film> getFilmByLikes(@RequestParam(defaultValue = "9") int count) {

        return filmService.getFilmByLikes(count);
    }
}
