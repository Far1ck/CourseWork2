package org.skypro.examservice.controller;

import org.skypro.examservice.model.question.Question;
import org.skypro.examservice.service.examservices.ExaminerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/java/{amount}")
    public Collection<Question> getJavaQuestions(@PathVariable int amount) {
        return examinerService.getJavaQuestions(amount);
    }

    @GetMapping("/math/{amount}")
    public Collection<Question> getMathQuestions(@PathVariable int amount) {
        return examinerService.getMathQuestions(amount);
    }
}
