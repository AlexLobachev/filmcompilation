package ru.yandex.practicum.filmcompilation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotFound extends RuntimeException {

    String message;
}
