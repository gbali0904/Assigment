package com.android.assignment.search.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.assignment.searchdetail.SearchDetailFragment;
import com.android.assignment.searchlist.SearchListFragment;
import com.android.assignment.searchlist.model.ModelForSearchList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private final String TITLE_SEARCH_LIST = "List";
    private final String TITLE_SEARCH_DETAILS = "Details";

    SearchListFragment searchListFragment;

    SearchDetailFragment searchDetailFragment;

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    FragmentManager oFragmentManager;

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

    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }


    public void showSearchList(String projectType, String projectLanguage) {

        if (null == searchListFragment) {
            searchListFragment= SearchListFragment.newInstance(projectType, projectLanguage);
            mFragmentList.add(searchListFragment);
            mFragmentTitleList.add(TITLE_SEARCH_LIST);
            notifyDataSetChanged();
        } else {
            searchListFragment.loadNewData(projectType, projectLanguage);
        }
    }

    public void showDetails(ModelForSearchList.ItemsBean itemdata) {
        if (null == searchDetailFragment) {
            searchDetailFragment=  SearchDetailFragment.newInstance(itemdata);
            mFragmentList.add(searchDetailFragment);
            mFragmentTitleList.add(TITLE_SEARCH_DETAILS);
            notifyDataSetChanged();
        } else {
            searchDetailFragment.loadNewData(itemdata);
        }
    }

    public void hideDetails() {
        mFragmentList.remove(searchDetailFragment);
        mFragmentTitleList.remove(TITLE_SEARCH_DETAILS);
        searchDetailFragment = null;
        notifyDataSetChanged();
    }

}
