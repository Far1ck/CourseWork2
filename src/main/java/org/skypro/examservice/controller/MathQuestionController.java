package org.skypro.examservice.controller;

import org.skypro.examservice.model.question.Question;
import org.skypro.examservice.service.QuestionService;
import org.skypro.examservice.service.mathservices.MathQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class MathQuestionController {
    private QuestionService mathQuestionService;

    public MathQuestionController(MathQuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }

    @GetMapping("/math/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return mathQuestionService.add(question, answer);
    }

    @GetMapping("/math/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        Question result = new Question(question, answer);
        return mathQuestionService.remove(result);
    }

    @GetMapping("/math")
    public Collection<Question> getQuestions() {
        return mathQuestionService.getAll();
    }
}
