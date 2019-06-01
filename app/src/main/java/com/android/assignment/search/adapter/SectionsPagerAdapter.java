package com.android.assignment.search.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    FragmentManager oFragmentManager;
    ArrayList<Fragment> oPooledFragments;
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);

        oFragmentManager=fm;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public int getItemPosition(Object object){
        Fragment fragment = (Fragment) object;
        int fragmentPosition = mFragmentList.indexOf(fragment);

        if (fragmentPosition >= 0) {
            // The current data matches the data in this active fragment, so let it be as it is.
            return fragmentPosition;
        } else {
            // Returning POSITION_NONE means the current data does not matches the data this fragment is showing right now.  Returning POSITION_NONE constant will force the fragment to redraw its view layout all over again and show new data.
            return POSITION_NONE;
        }

    }

    public void addFrag(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }


}
