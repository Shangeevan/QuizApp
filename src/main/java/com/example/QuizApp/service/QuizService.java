package com.example.QuizApp.service;

import com.example.QuizApp.repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        return new ResponseEntity<>("dsfsdaf", HttpStatus.OK);
    }
}
