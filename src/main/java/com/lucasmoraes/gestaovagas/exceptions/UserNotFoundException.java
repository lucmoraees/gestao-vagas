package com.lucasmoraes.gestaovagas.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found!");
    }
}