package org.skypro.examservice.service.examservices;

import org.skypro.examservice.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ExaminerService {
    Collection<Question> getJavaQuestions(int amount);
    Collection<Question> getMathQuestions(int amount);
}
