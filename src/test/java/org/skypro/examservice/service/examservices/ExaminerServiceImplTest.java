package org.skypro.examservice.service.examservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.examservice.model.error.NoSoManyQuestionsException;
import org.skypro.examservice.model.question.Question;
import org.skypro.examservice.service.javaservices.JavaQuestionService;
import org.skypro.examservice.service.mathservices.MathQuestionService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @Mock
    private MathQuestionService mathQuestionService;
    @InjectMocks
    private ExaminerServiceImpl examinerServiceImpl;

    @Test
    void getSomeJavaQuestionsFromEmptyQuestionList_ExceptionIsThrown() {
        int amount = 3;
        Set<Question> questionSet = new HashSet<>();
        when(javaQuestionService.getAll()).thenReturn(questionSet);
        assertThrows(NoSoManyQuestionsException.class, () -> examinerServiceImpl.getJavaQuestions(amount));
        verify(javaQuestionService, times(1)).getAll();
    }

    @Test
    void getSomeJavaQuestionsFromSimpleQuestionList_ReturnCollection() {
        int amount = 2;
        Set<Question> questionSet = new HashSet<>();
        Question question1 = new Question("1", "1");
        Question question2 = new Question("2", "2");
        Question question3 = new Question("3", "3");
        questionSet.add(question1);
        questionSet.add(question2);
        questionSet.add(question3);
        when(javaQuestionService.getAll()).thenReturn(questionSet);
        when(javaQuestionService.getRandomQuestion()).thenReturn(question1, question2, question3);
        Collection<Question> result = examinerServiceImpl.getJavaQuestions(amount);
        assertEquals(amount, result.size());
        verify(javaQuestionService, times(1)).getAll();
        verify(javaQuestionService, atLeast(2)).getRandomQuestion();
    }

    @Test
    void getSomeMathQuestions_ReturnCollection() {
        int amount = 2;
        Question question1 = new Question("1 + 1", "2");
        Question question2 = new Question("2 * 2", "4");
        Question question3 = new Question("10 / 2", "5");
        when(mathQuestionService.getRandomQuestion()).thenReturn(question1, question2, question3);
        Collection<Question> result = examinerServiceImpl.getMathQuestions(amount);
        assertEquals(amount, result.size());
        verify(mathQuestionService, atLeast(2)).getRandomQuestion();
    }

    @Test
    void getZeroMathQuestions_ReturnEmptyCollection() {
        int amount = 0;
        Collection<Question> result = examinerServiceImpl.getMathQuestions(amount);
        assertEquals(amount, result.size());
        verify(mathQuestionService, times(0)).getRandomQuestion();
    }
}
