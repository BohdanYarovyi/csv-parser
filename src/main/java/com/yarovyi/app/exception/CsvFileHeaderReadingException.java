package com.yarovyi.app.exception;

public class CsvFileHeaderReadingException extends CsvFileReadingException {

    public CsvFileHeaderReadingException() {}

    public CsvFileHeaderReadingException(String message) {
        super(message);
    }

    public CsvFileHeaderReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvFileHeaderReadingException(Throwable cause) {
        super(cause);
    }

}
