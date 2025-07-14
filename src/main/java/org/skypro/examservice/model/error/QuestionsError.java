package org.skypro.examservice.model.error;

public class QuestionsError {
    private String code;
    private String message;

    public QuestionsError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
