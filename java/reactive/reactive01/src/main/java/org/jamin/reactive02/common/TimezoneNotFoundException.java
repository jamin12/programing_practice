package org.jamin.reactive02.common;

public class TimezoneNotFoundException extends RuntimeException {
    public TimezoneNotFoundException(String message) {
        super(message);
    }
}