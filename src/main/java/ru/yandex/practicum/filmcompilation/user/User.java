package ru.yandex.practicum.filmcompilation.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

/**
 * Сущность пользователей(П)
 *
 * @author Алексей Лобачёв
 * @id - идентификатор (П)
 * @email - уникальный электронный адрес (П)
 * @login - уникальный логин (П)
 * @name - имя (П). Предполагается, что если оно не задано, оно присваивается по логину
 * @birthday - день рождения (П)
 * @версия 1.0
 */
@Data
public class User {
    private long id;
    @Email
    @NotBlank
    private final String email;
    @NotBlank
    private final String login;
    private String name;
    @Past
    private final LocalDate birthday;
}
