package com.example.dell.registrationpage;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class QuizAdapter_Student extends ArrayAdapter<QuizView_Student>
{

    public QuizAdapter_Student(Context context, List<QuizView_Student> resource) {
        super(context,0,resource);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(
                    R.layout.quiz_list_item_student, parent, false
            );
        }
        QuizView_Student currentQuiz=getItem(position);
        TextView scoreView=(TextView) listItemView.findViewById(R.id.score);
        double score=currentQuiz.getScore();
        String formattedScore=formatScore(score);
        scoreView.setText(formattedScore);
        GradientDrawable scoreCircle = (GradientDrawable) scoreView.getBackground();

        int scoreColor = getScoreColor(currentQuiz.getScore());
        scoreCircle.setColor(scoreColor);

        String quizName = currentQuiz.getQuizName();
        TextView quizNameView=(TextView) listItemView.findViewById(R.id.quiz_name);
        quizNameView.setText(quizName);

        String date=currentQuiz.getDate();
        TextView dateView=(TextView) listItemView.findViewById(R.id.date);
        dateView.setText(date);

        String time=currentQuiz.getTime();
        TextView timeView=(TextView) listItemView.findViewById(R.id.time);
        timeView.setText(time);

        return listItemView;
    }
    private String formatScore(double score) {
        DecimalFormat scoreFormat = new DecimalFormat("0.0");
        return scoreFormat.format(score);
    }
    private int getScoreColor(double score) {
        int scoreColorResourceId;
        int scoreFloor = (int) Math.floor(score);
        scoreColorResourceId=R.color.score_color;
        return ContextCompat.getColor(getContext(), scoreColorResourceId);
    }
}
