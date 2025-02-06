package ru.yandex.practicum.filmcompilation.service;


import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmcompilation.model.Film;
import ru.yandex.practicum.filmcompilation.model.User;
import ru.yandex.practicum.filmcompilation.storage.FilmStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Бизнес логика фильмов(Ф)
 *
 * @author Алексей Лобачёв
 * @версия 1.0
 */
@Service
public class FilmServiceImpl implements FilmService {
    private final FilmStorage filmStorage;

    public FilmServiceImpl(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @Override
    public Map<String, String> addLike(long idFilm, User user) {
        filmStorage.getFilmById(idFilm).getLike().add(user);
        return Map.of(String.format("Пользователь <%s> поставил фильму <%s> +1",
                        user.getName(), filmStorage.getFilmById(idFilm).getName()),
                String.format(" - рейтинг фильма: <%s>",
                        filmStorage.getFilmById(idFilm).getLike().size()));
    }

    @Override
    public Map<String, String> deleteLike(long idFilm, User user) {
        filmStorage.getFilmById(idFilm).getLike().remove(user);
        return null;
    }

    @Override
    public List<Film> getFilmByLikes(int count) {
        List<Film> films = new ArrayList<>(filmStorage.getAllFilms());
        Comparator<Film> likeComparator = new TopLikeComparator();
        films.sort(likeComparator);
        if (films.size() < count)
            count = films.size();
        return new ArrayList<>(films.subList(0, count));
    }
}

