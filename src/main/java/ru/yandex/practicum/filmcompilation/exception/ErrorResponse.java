package ru.yandex.practicum.filmcompilation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    protected String error;
    protected String description;
}
