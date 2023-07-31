package com.example.QuizApp.controller;

import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Model.QuizWrapper;
import com.example.QuizApp.dtos.SubmitAnswer;
import com.example.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<List<Question>> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuizWrapper>> getQuizQuestion(@PathVariable Integer id){
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submitAnswer/{id}")
    public ResponseEntity<Integer> submitAnswer(@PathVariable Integer id,@RequestBody List<SubmitAnswer> submitAnswer){
        return quizService.submitAnswer(id,submitAnswer);
    }


}
