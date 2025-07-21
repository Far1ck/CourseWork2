package org.skypro.examservice.model.error;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExamControllerAdvice {
    @ExceptionHandler(NoSoManyQuestionsException.class)
    public ResponseEntity<QuestionsError> handleNoSoManyQuestionsException() {
        return new ResponseEntity<>(new QuestionsError("400", "BAD_REQUEST: Too many questions requested"),
                HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(QuestionIsEmptyException.class)
    public ResponseEntity<QuestionsError> questionIsEmptyExceptionHandler(QuestionIsEmptyException e) {
        return new ResponseEntity<>(new QuestionsError("400", "BAD_REQUEST: " + e.getMessage()),
                HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<QuestionsError> methodNotAllowedExceptionHandler(MethodNotAllowedException e) {
        return new ResponseEntity<>(new QuestionsError("405", e.getMessage()),
                HttpStatusCode.valueOf(405));
    }
}
