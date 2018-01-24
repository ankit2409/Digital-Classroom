package com.example.dell.registrationpage;

/**
 * Created by Dell on 1/18/2018.
 */

public class StudentInfo {
    private String name;
    private String email;
    private String id;
    private String password;
    private String batch;
    private String year;
    private String department;
    private int score;
    private String image;

    public StudentInfo() {
    }

    public StudentInfo(String name, String email, String id, String password, String batch, String year, String department, int score, String image) {
        this.name = name;
        this.email = email;

        this.id = id;
        this.password = password;
        this.batch = batch;
        this.year = year;
        this.department = department;
        this.score=score;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

