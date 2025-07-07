package com.yarovyi.app.exception;

public class HeaderValidationException extends Exception {
  public HeaderValidationException() {
  }

  public HeaderValidationException(String message) {
        super(message);
    }

  public HeaderValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public HeaderValidationException(Throwable cause) {
    super(cause);
  }

  public HeaderValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
