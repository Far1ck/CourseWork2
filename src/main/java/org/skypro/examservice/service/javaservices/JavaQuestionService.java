package org.skypro.examservice.service.javaservices;

import org.skypro.examservice.model.question.Question;
import org.skypro.examservice.service.QuestionRepository;
import org.skypro.examservice.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {
    Random random = new Random();
    private QuestionRepository javaQuestionRepository;

    public JavaQuestionService(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    public Question add(String question, String answer) {
        return javaQuestionRepository.add(question, answer);
    }

    public Question add(Question question) {
        return javaQuestionRepository.add(question);
    }

    public Question remove(Question question) {
        return javaQuestionRepository.remove(question);
    }

    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        int index = random.nextInt(Integer.MAX_VALUE);
        List<Question> questionList = javaQuestionRepository.getAll().stream().toList();
        if (questionList.isEmpty()) {
            return null;
        } else if (index < questionList.size()) {
            return questionList.get(index);
        } else {
            return questionList.get(index % questionList.size());
        }
    }
}
