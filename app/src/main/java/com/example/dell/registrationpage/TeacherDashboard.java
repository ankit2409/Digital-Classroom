package com.example.dell.registrationpage;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TeacherDashboard extends AppCompatActivity implements Quizzes_Teacher.OnFragmentInteractionListener,Assignments_Teacher.OnFragmentInteractionListener,Announcement_Teacher.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        TabLayout tabLayout=(TabLayout) findViewById(R.id.tabLayout_teacher);
        tabLayout.addTab(tabLayout.newTab().setText("Quizzes_Teacher"));
        tabLayout.addTab(tabLayout.newTab().setText("Assignments_Teacher"));
        tabLayout.addTab(tabLayout.newTab().setText("Announcements"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager=(ViewPager) findViewById(R.id.pager_teacher);
        final PagerAdapter pagerAdapter=new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
