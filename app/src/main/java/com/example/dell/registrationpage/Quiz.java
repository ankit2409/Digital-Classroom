package com.example.dell.registrationpage;

/**
 * Created by Dell on 1/20/2018.
 */

public class Quiz {
    String quizName;
    String quizDate;
    String quizTime;
    String noOfQuestions;

    public Quiz(String quizName, String quizDate, String quizTime,String noOfQuestions) {
        this.quizName = quizName;
        this.quizDate = quizDate;
        this.quizTime = quizTime;
        this.noOfQuestions=noOfQuestions;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizDate() {
        return quizDate;
    }

    public void setQuizDate(String quizDate) {
        this.quizDate = quizDate;
    }

    public String getQuizTime() {
        return quizTime;
    }

    public void setQuizTime(String quizTime) {
        this.quizTime = quizTime;
    }

    public String getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(String noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }
}
