package com.example.dell.registrationpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Dell on 1/21/2018.
 */

public class PageAdapterStudent  extends FragmentStatePagerAdapter {
    int mNoOfTabs;
    public PageAdapterStudent(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        mNoOfTabs=NumberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Quizzes_Student quizzes=new Quizzes_Student();
                return quizzes;

            case 1:
                Assignments_Student assignments=new Assignments_Student();
                return assignments;

            case 2:
                Notifications_Student announcement=new Notifications_Student();
                return announcement;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}

