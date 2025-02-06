package ru.yandex.practicum.filmcompilation.service;

import ru.yandex.practicum.filmcompilation.model.Film;

import java.util.Comparator;

public class TopLikeComparator implements Comparator<Film> {
    @Override
    public int compare(Film o1, Film o2) {
        return o2.getLike().size() - o1.getLike().size();
    }
}
