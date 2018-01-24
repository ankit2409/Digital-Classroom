package com.example.dell.registrationpage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class StudentDash extends AppCompatActivity implements Quizzes_Student.OnFragmentInteractionListener,Assignments_Student.OnFragmentInteractionListener,Notifications_Student.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash);
        TabLayout tabLayout_student=(TabLayout)findViewById(R.id.tabLayout_student);
        tabLayout_student.addTab(tabLayout_student.newTab().setText("Quizzes"));
        tabLayout_student.addTab(tabLayout_student.newTab().setText("Assignments"));
        tabLayout_student.addTab(tabLayout_student.newTab().setText("Notifications"));
        tabLayout_student.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager=(ViewPager)findViewById(R.id.pager_student);
        final PageAdapterStudent adapter=new PageAdapterStudent(getSupportFragmentManager(),tabLayout_student.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout_student));
        tabLayout_student.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        TextView student_dash=(TextView)findViewById(R.id.stu_dash);
        student_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDash.this,PersonalDetailsActivity_Student.class));
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
