package org.skypro.examservice.service.javaservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.examservice.model.error.QuestionIsEmptyException;
import org.skypro.examservice.model.question.Question;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionRepositoryTest {
    @InjectMocks
    private JavaQuestionRepository javaQuestionRepository;

    @Test
    void addCorrectQuestionAndAnswerText_Success() {
        String question = "Question Text";
        String answer = "Question Answer";

        Question result = javaQuestionRepository.add(question, answer);

        assertEquals(question, result.getQuestion());
        assertEquals(answer, result.getAnswer());
        assertEquals(new Question(question, answer), result);
    }

    @Test
    void addIncorrectQuestionAndAnswerText_ExceptionIsThrown() {
        String question = "Question Text";
        String answer = "   ";

        assertThrows(QuestionIsEmptyException.class, () -> javaQuestionRepository.add(question, answer));
    }

    @Test
    void addCorrectQuestion_Success() {
        String questionText = "Question Text";
        String answer = "Question Answer";
        Question question = new Question(questionText, answer);

        Question result = javaQuestionRepository.add(question);

        assertEquals(questionText, result.getQuestion());
        assertEquals(answer, result.getAnswer());
        assertEquals(question, result);
    }

    @Test
    void addIncorrectQuestion_ExceptionIsThrown() {
        String questionText = "Question Text";
        String answer = "   ";
        Question question = new Question(questionText, answer);

        assertThrows(QuestionIsEmptyException.class, () -> javaQuestionRepository.add(question));
    }

    @Test
    void removeCorrectQuestion_Success() {
        String questionText = "Question Text";
        String answer = "Question Answer";
        Question question = new Question(questionText, answer);

        Question result = javaQuestionRepository.remove(question);

        assertEquals(questionText, result.getQuestion());
        assertEquals(answer, result.getAnswer());
        assertEquals(question, result);
    }

    @Test
    void removeIncorrectQuestion_ExceptionIsThrown() {
        String questionText = "Question Text";
        String answer = "   ";
        Question question = new Question(questionText, answer);

        assertThrows(QuestionIsEmptyException.class, () -> javaQuestionRepository.remove(question));
    }

    @Test
    void getAll_ReturnCollection() {
        Collection<Question> result = javaQuestionRepository.getAll();

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
