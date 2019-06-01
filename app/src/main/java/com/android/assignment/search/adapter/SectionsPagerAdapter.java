package com.android.assignment.search.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.assignment.detail.SearchDetailFragment;
import com.android.assignment.list.SearchListFragment;
import com.android.assignment.utility.Constants;

public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private final String project_type;
    private final String project_language;

    public SectionsPagerAdapter(FragmentManager fm, String project_type, String project_language) {
        super(fm);
      this.project_type= project_type;
      this.project_language= project_language;

    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0:
                SearchListFragment searchListFragment=new SearchListFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.TYPE, project_type);
                bundle.putString(Constants.LANGUAGE, project_language);
                searchListFragment.setArguments(bundle);
                return searchListFragment;

            case 1:
                SearchDetailFragment searchDetailFragment=new SearchDetailFragment();
                return  searchDetailFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }


}
