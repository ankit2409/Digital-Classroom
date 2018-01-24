package com.example.dell.registrationpage;

/**
 * Created by hp on 1/20/2018.
 */

public class QuizView_Student
{
    private int score;
    private String quizName;
    private String date;
    private String time;

    public QuizView_Student(int score, String quizName, String date, String time) {
        this.score = score;
        this.quizName = quizName;
        this.date = date;
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
