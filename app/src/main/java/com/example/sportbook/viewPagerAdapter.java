package com.example.sportbook;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sportbook.basketballFragment;
import com.example.sportbook.footballFragment;
import com.example.sportbook.tennisFragment;

public class viewPagerAdapter extends FragmentStatePagerAdapter {
    private int numOfTabs;

    public viewPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                footballFragment footballFragment = new footballFragment();
                return footballFragment;
            case 1:
                tennisFragment tennisFragment = new tennisFragment();
                return tennisFragment;
            case 2:
                basketballFragment basketballFragment = new basketballFragment();
                return basketballFragment;

        }
        return null;


    }


    @Override
    public int getCount() {
        return numOfTabs;
    }

    public int getItemPosition(Object object){
        return POSITION_NONE;
    }

}
