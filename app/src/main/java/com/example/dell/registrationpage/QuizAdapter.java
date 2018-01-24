package com.example.dell.registrationpage;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Dell on 1/20/2018.
 */

public class QuizAdapter extends ArrayAdapter<QuizListItem> {


    public QuizAdapter(Context context,List<QuizListItem> resource) {
        super(context, 0,resource);
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(
                    R.layout.quizformat, parent, false
            );
        }
        QuizListItem currentQuiz=getItem(position);

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
    /*private String formatScore(double score) {
        DecimalFormat scoreFormat = new DecimalFormat("0.0");
        return scoreFormat.format(score);
    }
    private int getScoreColor(double score) {
        int scoreColorResourceId;
        int scoreFloor = (int) Math.floor(score);
        scoreColorResourceId=R.color.score_color;
        return ContextCompat.getColor(getContext(), scoreColorResourceId);
    }*/
}
