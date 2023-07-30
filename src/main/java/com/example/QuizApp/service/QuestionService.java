package com.example.QuizApp.service;

import com.example.QuizApp.Model.Question;

import com.example.QuizApp.repository.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;



    /**
     * ================================================================
     * Get all question
     * ================================================================
     * @return List of Question
     */
    public ResponseEntity<List<Question>> getAllQuestion() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    /**
     * ================================================================
     * Get all question based on the category
     * ================================================================
     * @return List of Question
     */
    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    /**
     * ================================================================
     * Add a question
     * ================================================================
     * @return String
     */
    public ResponseEntity<String> addQuestion(Question question) {
        try{
             questionDao.save(question);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Successful",HttpStatus.CREATED);
    }

    /**
     * ================================================================
     * Delete a Question
     * ================================================================
     * @return String
     */
    public ResponseEntity<String> deleteQuestion(Integer id) {
        try{
            questionDao.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Delete Successfully",HttpStatus.OK);
    }

    /**
     * ================================================================
     * Update a question
     * ================================================================
     * @return Container
     */
    public ResponseEntity<String> updateQuestion(Question question,Integer id) {
        try{
           Question existQuestion =  questionDao.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
            System.out.println(existQuestion.toString());
            existQuestion.setId(question.getId());
            existQuestion.setQuestionTitle(question.getQuestionTitle());
            existQuestion.setOption1(question.getOption1());
            existQuestion.setOption2(question.getOption2());
            existQuestion.setOption3(question.getOption3());
            existQuestion.setOption4(question.getOption4());
            existQuestion.setRightAnswer(question.getRightAnswer());
            existQuestion.setDifficultylevel(question.getDifficultylevel());
            existQuestion.setCategory(question.getCategory());
           questionDao.save(existQuestion);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
    }
}