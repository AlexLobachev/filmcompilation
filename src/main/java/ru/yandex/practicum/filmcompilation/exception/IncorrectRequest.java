package ru.yandex.practicum.filmcompilation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IncorrectRequest extends RuntimeException{
    private final String message;
}
