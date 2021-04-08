package org.assignment.exception;

public class FileTypeMismatch extends RuntimeException{
    public FileTypeMismatch(String message) {
        super(message);
    }
}
