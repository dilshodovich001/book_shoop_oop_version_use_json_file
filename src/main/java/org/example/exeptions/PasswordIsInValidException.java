package org.example.exeptions;

public class PasswordIsInValidException extends RuntimeException {
    public PasswordIsInValidException(String message) {
        super(message);
    }
}
