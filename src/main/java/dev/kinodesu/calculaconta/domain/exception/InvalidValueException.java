package dev.kinodesu.calculaconta.domain.exception;

import lombok.Getter;

@Getter
public class InvalidValueException extends RuntimeException {

    public InvalidValueException(String message) {
        super(message);
    }
}
