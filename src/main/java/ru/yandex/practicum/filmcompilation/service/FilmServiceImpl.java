package ru.yandex.practicum.filmcompilation.service;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmcompilation.film.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Бизнес логика фильмов(Ф)
 *
 * @author Алексей Лобачёв
 * @версия 1.0
 */
@Slf4j
public class FilmServiceImpl implements FilmService {
    private long id = 0;
    private final Map<Long, Film> filmMap = new HashMap<>();

    @Override
    public Film createFilm(Film film) {
        validation(film);
        film.setId(generationId());
        filmMap.put(film.getId(), film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (!filmMap.containsKey(film.getId())){
            throw new ValidationException("Фильм не найден");
        }
        validation(film);
        filmMap.put(film.getId(), film);
        return film;
    }

    @Override
    public List<Film> getAllFilms() {
        return new ArrayList<>(filmMap.values());
    }

    private long generationId() {
        id++;
        return id;
    }
    private void validation(Film film){
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Дата не может быть раньше " + LocalDate.of(1895, 12, 28));
        }

        if (film.getDuration()<0){
            throw new ValidationException("Фильм не может иметь отрицательную длительность");
        }
    }
}
