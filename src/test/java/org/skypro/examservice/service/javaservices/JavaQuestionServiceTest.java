package org.skypro.examservice.service.javaservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.examservice.model.error.QuestionIsEmptyException;
import org.skypro.examservice.model.question.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {
    @Mock
    private JavaQuestionRepository JavaQuestionRepository;

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Test
    void addCorrectQuestionAndAnswer_Success() {
        String question = "Question Text";
        String answer = "Question Answer";
        when(JavaQuestionRepository.add(question, answer)).thenReturn(new Question(question, answer));

        Question result = javaQuestionService.add(question, answer);

        assertEquals(question, result.getQuestion());
        assertEquals(answer, result.getAnswer());
        verify(JavaQuestionRepository, times(1)).add(question, answer);
    }

    @Test
    void addCorrectQuestionAndIncorrectAnswer_ExceptionIsThrown() {
        String question = "Question Text";
        String answer = "";
        when(JavaQuestionRepository.add(question, answer)).thenThrow(QuestionIsEmptyException.class);

        assertThrows(QuestionIsEmptyException.class, () -> javaQuestionService.add(question, answer));
        verify(JavaQuestionRepository, times(1)).add(question, answer);
    }

    @Test
    void addCorrectQuestion_Success() {
        Question question = new Question("Question Text", "Question Answer");
        when(JavaQuestionRepository.add(question)).thenReturn(question);

        Question result = javaQuestionService.add(question);

        assertEquals(question.getQuestion(), result.getQuestion());
        assertEquals(question.getAnswer(), result.getAnswer());
        verify(JavaQuestionRepository, times(1)).add(question);
    }

    @Test
    void addIncorrectQuestion_ExceptionIsThrown() {
        Question question = new Question("Question Text", "");
        when(JavaQuestionRepository.add(question)).thenThrow(QuestionIsEmptyException.class);

        assertThrows(QuestionIsEmptyException.class, () -> javaQuestionService.add(question));
        verify(JavaQuestionRepository, times(1)).add(question);
    }

    @Test
    void removeCorrectQuestion_Success() {
        Question question = new Question("Question Text", "Question Answer");
        when(JavaQuestionRepository.remove(question)).thenReturn(question);

        Question result = javaQuestionService.remove(question);

        assertEquals(question.getQuestion(), result.getQuestion());
        assertEquals(question.getAnswer(), result.getAnswer());
        verify(JavaQuestionRepository, times(1)).remove(question);
    }

    @Test
    void removeNullQuestion_ExceptionIsThrown() {
        Question question = null;
        when(JavaQuestionRepository.remove(question)).thenThrow(QuestionIsEmptyException.class);

        assertThrows(QuestionIsEmptyException.class, () -> javaQuestionService.remove(question));
        verify(JavaQuestionRepository, times(1)).remove(question);
    }

    @Test
    void getAllWithEmptyQuestionsSet_ReturnEmptyQuestionSet() {
        Set<Question> questionSet = new HashSet<>();
        when(JavaQuestionRepository.getAll()).thenReturn(questionSet);

        Collection<Question> result = javaQuestionService.getAll();

        assertEquals(questionSet.size(), result.size());
        assertEquals(questionSet.isEmpty(), result.isEmpty());
        verify(JavaQuestionRepository, times(1)).getAll();
    }

    @Test
    void getRandomQuestionWithEmptyQuestionSet_ReturnNull() {
        Set<Question> questionSet = new HashSet<>();
        when(JavaQuestionRepository.getAll()).thenReturn(questionSet);

        Question result = javaQuestionService.getRandomQuestion();

        assertNull(result);
        verify(JavaQuestionRepository, times(1)).getAll();
    }

    @Test
    void getRandomQuestionWithSimpleQuestionSet_ReturnRandomQuestion() {
        Set<Question> questionSet = new HashSet<>();
        Question question1 = new Question("1", "1");
        Question question2 = new Question("2", "2");
        questionSet.add(question1);
        questionSet.add(question2);
        when(JavaQuestionRepository.getAll()).thenReturn(questionSet);

        Question result = javaQuestionService.getRandomQuestion();

        assertTrue(result.getQuestion().equals("1") || result.getQuestion().equals("2"));
        verify(JavaQuestionRepository, times(1)).getAll();
    }
}
