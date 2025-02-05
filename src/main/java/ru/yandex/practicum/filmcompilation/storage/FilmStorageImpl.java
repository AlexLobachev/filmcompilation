package ru.yandex.practicum.filmcompilation.storage;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmcompilation.model.Film;

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
@Component
public class FilmStorageImpl implements FilmStorage {
    private long id = 0;
    private final Map<Long, Film> filmMap = new HashMap<>();

    @Override
    public Film createFilm(Film film) {
        film.setId(generationId());
        filmMap.put(film.getId(), film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        filmMap.put(film.getId(), film);
        return film;
    }
    @Override
    public Film getFilmById(long filmId){
        return filmMap.get(id);
    }

    @Override
    public List<Film> getAllFilms() {
        return new ArrayList<>(filmMap.values());
    }


    private long generationId() {
        id++;
        return id;
    }
}
