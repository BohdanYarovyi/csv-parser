package com.yarovyi.app.exception;

public class NotFoundMappedFieldNameException extends RuntimeException {
    public NotFoundMappedFieldNameException() {
    }

    public NotFoundMappedFieldNameException(String message) {
        super(message);
    }

    public NotFoundMappedFieldNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundMappedFieldNameException(Throwable cause) {
        super(cause);
    }

    public NotFoundMappedFieldNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
