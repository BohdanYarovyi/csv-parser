package com.yarovyi.app.exception;

public class CsvFileReadingException extends Exception {
    public CsvFileReadingException() {}

    public CsvFileReadingException(String message) {
        super(message);
    }

    public CsvFileReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvFileReadingException(Throwable cause) {
        super(cause);
    }

    public CsvFileReadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
