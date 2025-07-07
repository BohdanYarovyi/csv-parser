package com.yarovyi.app.exception;

public class InvalidDelimiterException extends HeaderValidationException {

    public InvalidDelimiterException() {
    }

    public InvalidDelimiterException(String message) {
        super(message);
    }

    public InvalidDelimiterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDelimiterException(Throwable cause) {
        super(cause);
    }

    public InvalidDelimiterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
