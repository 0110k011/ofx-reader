package com.ofxreader.exception;

public class OFXException extends RuntimeException {

    private final int status;

    public OFXException(String message, int status) {
        super(message);
        this.status = status;
    }

    public OFXException(String message, Throwable cause, int status) {
        super(cause.getMessage(), cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
