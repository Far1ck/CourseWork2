package org.skypro.examservice.service;

import org.skypro.examservice.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface QuestionRepository {
    Question add(String question, String answer);
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();
}
