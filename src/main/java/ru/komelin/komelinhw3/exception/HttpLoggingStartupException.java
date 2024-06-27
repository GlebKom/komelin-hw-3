package ru.komelin.komelinhw3.exception;

public class HttpLoggingStartupException extends RuntimeException{

    public HttpLoggingStartupException() {
        super();
    }

    public HttpLoggingStartupException(String message) {
        super(message);
    }
}
