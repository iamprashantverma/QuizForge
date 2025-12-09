package com.prashant.quizforge.server.exception;

public class DuplicateActionException extends RuntimeException{

    public DuplicateActionException(String message) {
        super(message);
    }
}
