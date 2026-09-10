package dev.kinodesu.calculaconta.infrastructure.config;

import dev.kinodesu.calculaconta.domain.entity.Error;
import dev.kinodesu.calculaconta.domain.entity.Errors;
import dev.kinodesu.calculaconta.domain.exception.InvalidValueException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerConfig {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Errors> handlerEntityNotFoundException(EntityNotFoundException ex){
        Errors errors = new Errors().addErrorsItem(new Error(HttpStatus.NOT_FOUND.toString(), ex.getMessage(), Error.LevelEnum.ERROR, ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<Errors> handlerInvalidItemQuantityException(InvalidValueException ex){
        Errors errors = new Errors().addErrorsItem(new Error(HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), Error.LevelEnum.ERROR, ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
