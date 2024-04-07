package com.platzi.pizzeria.service.exception;

public class EmailAPIException extends RuntimeException{
    public EmailAPIException() {
        super("Error sending email...");
    }
}
