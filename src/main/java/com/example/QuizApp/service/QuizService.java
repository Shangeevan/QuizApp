package com.example.QuizApp.service;

import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Model.Quiz;
import com.example.QuizApp.repository.QuestionDao;
import com.example.QuizApp.repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    /**
     * ================================================================
     * Generate Random question based on the parameters
     * ================================================================
     * @return List of Question
     */
    public ResponseEntity<List<Question>> createQuiz(String category,int numQ,String title) {
        List<Question> questions = questionDao.findRandomQuestionByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }




}
