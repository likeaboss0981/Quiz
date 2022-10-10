package com.example.myapplication;

public class Question {
    private int questionId;
    private boolean trueAnswer;

    public Question(int questionId, boolean trueAnswer){
        this.questionId = questionId;
        this.trueAnswer = trueAnswer;
    }

    public boolean isTrueAnswer() {
        if(trueAnswer == true){
            return true;
        }
        else
            return false;
    }

    public int getQuestionId() {
        return questionId;
    }
}
