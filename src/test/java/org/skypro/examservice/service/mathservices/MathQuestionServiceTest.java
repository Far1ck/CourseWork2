package org.skypro.examservice.service.mathservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.examservice.model.error.MethodNotAllowedException;
import org.skypro.examservice.model.question.Question;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {
    @InjectMocks
    private MathQuestionService mathQuestionService;

    @Test
    void addCorrectQuestionTextAndQuestionAnswer_ExceptionIsThrown() {
        String question = "QuestionText";
        String answer = "QuestionAnswer";

        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.add(question, answer));
    }

    @Test
    void addCorrectQuestion_ExceptionIsThrown() {
        Question question = new Question("QuestionText", "QuestionAnswer");

        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.add(question));
    }

    @Test
    void removeQuestion_ExceptionIsThrown() {
        Question question = new Question("QuestionText", "QuestionAnswer");

        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.remove(question));
    }

    @Test
    void getAll_ExceptionIsThrown() {
        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.getAll());
    }

    @Test
    void getRandomQuestion() {
        Question result = mathQuestionService.getRandomQuestion();

        assertNotNull(result);
        assertNotNull(result.getQuestion());
        assertNotNull(result.getAnswer());
    }
}
