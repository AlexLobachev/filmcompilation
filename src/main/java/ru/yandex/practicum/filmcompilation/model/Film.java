package ru.yandex.practicum.filmcompilation.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность фильмов(Ф)
 *
 * @author Алексей Лобачёв
 * @id - идентификатор (Ф)
 * @name - уникальное название (Ф)
 * @description - описание (Ф)
 * @releaseDate - дата релиза (Ф). Предполагается, что дата релиза может быть неизвестна
 * @duration - продолжительность (Ф)
 * @версия 1.0
 */
@Data
public class Film {
    private long id;
    @NotBlank
    private final String name;
    @Length(max = 200)
    private final String description;
    private LocalDate releaseDate;
    private final Duration duration;
    private Set<User> like = new HashSet<>();

    public long getDuration() {
        return duration.toSeconds();
    }
}
