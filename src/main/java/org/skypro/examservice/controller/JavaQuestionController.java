package org.skypro.examservice.controller;

import org.skypro.examservice.model.question.Question;
import org.skypro.examservice.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class JavaQuestionController {
    private QuestionService javaQuestionService;

    public JavaQuestionController(QuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/java/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return javaQuestionService.add(question, answer);
    }

    @GetMapping("/java/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        Question result = new Question(question, answer);
        return javaQuestionService.remove(result);
    }

    @GetMapping("/java")
    public Collection<Question> getJavaQuestions() {
        return javaQuestionService.getAll();
    }
}
