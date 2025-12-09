package com.prashant.quizforge.server.exception;

public class QuizNotFinishedException extends RuntimeException {
    public QuizNotFinishedException(String message) {
        super(message);
    }
}
