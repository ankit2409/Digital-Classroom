package com.example.dell.registrationpage;

/**
 * Created by Dell on 1/20/2018.
 */

public class QuizListItem {

    private String quizName;
    private String date;
    private String time;

    public QuizListItem(String quizName, String date, String time)
    {
        this.quizName = quizName;
        this.date = date;
        this.time = time;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
