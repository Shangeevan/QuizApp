package com.example.QuizApp.service;

import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Model.Quiz;
import com.example.QuizApp.Model.QuizWrapper;
import com.example.QuizApp.dtos.SubmitAnswer;
import com.example.QuizApp.repository.QuestionDao;
import com.example.QuizApp.repository.QuizDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * ================================================================
     * Get Quiz based on Quiz Id
     * ================================================================
     * @return List of QuizWrapper
     */
    public ResponseEntity<List<QuizWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuizWrapper> questionForUser = new ArrayList<>();
        for(Question q : questionsFromDB){
            QuizWrapper quizWrapper = new QuizWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(quizWrapper);
        }
        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

//    public ResponseEntity<String> submitAnswer(List<SubmitAnswer> submitAnswer) {
//        int score=0;
////        SubmitAnswer submitAnswer1 = submitAnswer.get(2);
////        System.out.println(submitAnswer1.toString());
//
//        Integer quizeID =submitAnswer.get(2).getQuizeID();
////        System.out.println(quizeID);
//        Optional<Quiz> quiz = quizDao.findById(quizeID);
////        System.out.println(quiz.toString());
//        List<Question> questions= quiz.get().getQuestions();
////        System.out.println(questions.toString());
////        System.out.println(questions.get(1).getRightAnswer());
////        System.out.println(submitAnswer.get(1).getRightAnswer());
//        System.out.println("dsffsf");
//        for(int i=0;i<=questions.size()-1; i++){
//            String answerOption = submitAnswer.get(i).getRightAnswer();
//            Optional<Question> rowId = questionDao.findById(submitAnswer.get(i).getId());
//            String Useranswer = questionDao.findTheCorrectAnser(rowId,answerOption);
//            System.out.println(Useranswer);
////            if(questions.get(i).getRightAnswer().equals(submitAnswer.get(i).getRightAnswer())){
////                score++;
////
////            }
//        }
//        System.out.println(score);
//        return new ResponseEntity<>("Success",HttpStatus.OK);
//    }
//}

    public ResponseEntity<Integer> submitAnswer(Integer id,List<SubmitAnswer> submitAnswer) {
        Integer score =0;
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        for(int i=0; i<=questions.size()-1;i++){
            if(questions.get(i).getRightAnswer().equals(submitAnswer.get(i).getResponse())){
                score++;
            }
        }
       return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
