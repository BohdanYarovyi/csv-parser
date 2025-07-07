package com.yarovyi.app.exception;

public class CsvFileContentReadingException extends CsvFileReadingException {

    public CsvFileContentReadingException() {}

    public CsvFileContentReadingException(String message) {
        super(message);
    }

    public CsvFileContentReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvFileContentReadingException(Throwable cause) {
        super(cause);
    }

    public CsvFileContentReadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
