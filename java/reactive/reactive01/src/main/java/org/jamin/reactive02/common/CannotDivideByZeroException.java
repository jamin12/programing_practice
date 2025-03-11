package org.jamin.reactive02.common;

public class CannotDivideByZeroException extends RuntimeException {
    public CannotDivideByZeroException(String message) {
        super(message);
    }
}