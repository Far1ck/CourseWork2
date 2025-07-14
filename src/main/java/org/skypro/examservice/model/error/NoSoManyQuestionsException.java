package org.skypro.examservice.model.error;

public class NoSoManyQuestionsException extends RuntimeException {
    public NoSoManyQuestionsException(String message) {
        super(message);
    }
}
