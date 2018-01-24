package com.example.dell.registrationpage;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Dell on 1/19/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;
    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        mNoOfTabs=NumberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Quizzes_Teacher quizzes=new Quizzes_Teacher();
                return quizzes;

            case 1:
                Assignments_Teacher assignments=new Assignments_Teacher();
                return assignments;

            case 2:
                Announcement_Teacher announcement=new Announcement_Teacher();
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
