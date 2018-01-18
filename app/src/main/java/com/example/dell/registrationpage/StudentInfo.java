package com.example.dell.registrationpage;

/**
 * Created by Dell on 1/18/2018.
 */

public class StudentInfo {
    private String name;
    private String email;
    private String uid;
    private String password;
    private String batch;
    private String year;
    private String department;

    public StudentInfo(String name, String email, String uid, String password, String batch, String year, String department) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.password = password;
        this.batch = batch;
        this.year = year;
        this.department = department;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
}

