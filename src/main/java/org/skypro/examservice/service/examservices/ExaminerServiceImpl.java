package org.skypro.examservice.service.examservices;

import org.skypro.examservice.model.error.NoSoManyQuestionsException;
import org.skypro.examservice.model.question.Question;
import org.skypro.examservice.service.QuestionService;
import org.skypro.examservice.service.javaservices.JavaQuestionService;
import org.skypro.examservice.service.mathservices.MathQuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private List<QuestionService> questionServices;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService, MathQuestionService mathQuestionService) {
        questionServices = new ArrayList<>(2);
        questionServices.add(javaQuestionService);
        questionServices.add(mathQuestionService);
    }

    @Override
    public Collection<Question> getJavaQuestions(int amount) {
        Set<Question> questionSet = new HashSet<>();
        QuestionService javaQuestionService = questionServices.get(0);
        if (amount > javaQuestionService.getAll().size()) {
            throw new NoSoManyQuestionsException("Too many questions requested");
        }
        while (questionSet.size() < amount) {
            questionSet.add(javaQuestionService.getRandomQuestion());
        }
        return questionSet;
    }

    @Override
    public Collection<Question> getMathQuestions(int amount) {
        Set<Question> questionSet = new HashSet<>();
        QuestionService mathQuestionService = questionServices.get(1);
        while (questionSet.size() < amount) {
            questionSet.add(mathQuestionService.getRandomQuestion());
        }
        return questionSet;
    }
}
