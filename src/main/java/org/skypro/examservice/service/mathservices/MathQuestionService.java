package org.skypro.examservice.service.mathservices;

import org.skypro.examservice.model.error.MethodNotAllowedException;
import org.skypro.examservice.model.question.Question;
import org.skypro.examservice.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    Random random = new Random();

    public Question add(String question, String answer) {
        throw new MethodNotAllowedException("Method Not Allowed");
    }

    public Question add(Question question) {
        throw new MethodNotAllowedException("Method Not Allowed");
    }

    public Question remove(Question question) {
        throw new MethodNotAllowedException("Method Not Allowed");
    }

    public Collection<Question> getAll() {
        throw new MethodNotAllowedException("Method Not Allowed");
    }

    @Override
    public Question getRandomQuestion() {
        int a = random.nextInt();
        int b = random.nextInt();
        int sign = random.nextInt(4);
        String question = "";
        String answer = "";
        switch (sign) {
            case 0:
                question = a + " (+) " + b;
                answer = String.valueOf((long) a + b);
                break;
            case 1:
                question = a + " (-) " + b;
                answer = String.valueOf((long) a - b);
                break;
            case 2:
                question = a + " (*) " + b;
                answer = String.valueOf((long) a * b);
                break;
            case 3:
                if (b == 0) {
                    while (b == 0) {
                        b = random.nextInt();
                    }
                }
                question = a + " (/) " + b;
                answer = String.valueOf((double) a / b);
                break;
        }
        return new Question(question, answer);
    }
}
