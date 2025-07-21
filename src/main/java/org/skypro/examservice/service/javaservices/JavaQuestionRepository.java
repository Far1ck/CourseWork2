package org.skypro.examservice.service.javaservices;

import org.skypro.examservice.model.error.QuestionIsEmptyException;
import org.skypro.examservice.model.question.Question;
import org.skypro.examservice.service.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class JavaQuestionRepository implements QuestionRepository {
    private Set<Question> questions = new HashSet<>();

    public Question add(String question, String answer) {
        if ((question != null)&&(answer != null)&&
                (!question.trim().isEmpty())&&(!answer.trim().isEmpty())) {
            Question result = new Question(question, answer);
            questions.add(result);
            return result;
        } else {
            throw new QuestionIsEmptyException("Question or answer is empty");
        }
    }

    public Question add(Question question) {
        if ((question != null)&&(question.getQuestion() != null)&&(question.getAnswer() != null)&&
                (!question.getQuestion().trim().isEmpty())&&(!question.getAnswer().trim().isEmpty())) {
            questions.add(question);
            return question;
        } else {
            throw new QuestionIsEmptyException("Question is null");
        }
    }

    public Question remove(Question question) {
        if (question != null) {
            questions.remove(question);
            return question;
        } else {
            throw new QuestionIsEmptyException("Question is null");
        }
    }

    public Collection<Question> getAll() {
        return questions;
    }
}
