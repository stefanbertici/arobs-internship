package com.arobs.internship.musify.exception;

public class IncorrectEmailOrPasswordException extends RuntimeException{

    public IncorrectEmailOrPasswordException(String message) {
        super(message);
    }
}
