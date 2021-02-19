package com.format.exception;


public class UnknownAlignmentException extends RuntimeException {
    private final String message;

    public UnknownAlignmentException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
