package ru.yandex.practicum.filmcompilation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmcompilation.exception.ErrorResponse;
import ru.yandex.practicum.filmcompilation.exception.IncorrectRequest;
import ru.yandex.practicum.filmcompilation.exception.NotFound;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(IncorrectRequest e) {
        return new ErrorResponse("Ошибка!", e.getMessage());
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(NotFound e) {
        return new ErrorResponse("Ошибка!", e.getMessage());
    }



}
