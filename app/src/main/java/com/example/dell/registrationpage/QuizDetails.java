package com.example.dell.registrationpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuizDetails extends AppCompatActivity {
    private EditText quizName, quizDate, quizTime, noOfQuestion;
    Button uploadQuestion;

    String qName,aDate,qTime;
    int qnoOfques;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details);
        quizName = (EditText) findViewById(R.id.quizname);
        quizDate = (EditText)findViewById(R.id.quizDate);
        quizTime=(EditText)findViewById(R.id.quiztime);
        noOfQuestion=(EditText)findViewById(R.id.noOfQuestion);
        uploadQuestion=(Button)findViewById(R.id.writeQuestion);
        uploadQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qName=quizName.getText().toString().trim();
                qName=quizDate.getText().toString().trim();
                qTime=quizTime.getText().toString().trim();
                qnoOfques=Integer.parseInt(noOfQuestion.getText().toString().trim());
                //startActivity(new Intent(QuizDetails.this,Questions.class));
            }
        });

    }
}