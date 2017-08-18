package com.example.android.newprojectpractice;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sabina on 7/9/2017.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    public Context context;

    public FragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FeaturedBookFragment();
            case 1:
                return new NewBookFragment();
            case 2:
                return new MyLibraryFragment();
            case 3:
                return new SearchBookFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
            return " ";
    }
}
