package com.mediga.documentstore.exception;

public class DocumentAlreadyExistsException extends Exception{
    public DocumentAlreadyExistsException(String message) {
        super(message);
    }
}
