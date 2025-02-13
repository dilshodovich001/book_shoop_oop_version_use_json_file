package org.example.exeptions;

public class PhoneIsInValidException extends RuntimeException {
    public PhoneIsInValidException(String message) {
        super(message);
    }
}
