package ru.yandex.practicum.filmcompilation.exception;

public class ValidationException extends RuntimeException{
    public ValidationException (String message){
        super(message);
    }
}
