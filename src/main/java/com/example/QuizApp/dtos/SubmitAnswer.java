package com.example.QuizApp.dtos;

import lombok.Data;

@Data
public class SubmitAnswer {
    private Integer id;
    private String response;

    public SubmitAnswer(){

    }

    public SubmitAnswer(Integer id, String response) {
        this.id = id;
        this.response = response;
    }
}
