package ru.practicum.contest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(HttpStatusCodeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleNotFoundException(final HttpStatusCodeException e) {
        log.error(Arrays.toString(e.getStackTrace()));
        return new ErrorResponse(e.getStatusCode(), e.getCause().getMessage(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleNotFoundException(final RuntimeException e) {
        log.error(Arrays.toString(e.getStackTrace()));
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getMessage(), e.getMessage());
    }
}