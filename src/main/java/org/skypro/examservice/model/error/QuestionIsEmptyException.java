package org.skypro.examservice.model.error;

public class QuestionIsEmptyException extends RuntimeException {
    public QuestionIsEmptyException(String message) {
        super(message);
    }
}
