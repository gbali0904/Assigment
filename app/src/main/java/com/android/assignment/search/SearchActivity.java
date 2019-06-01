package com.android.assignment.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.assignment.R;
import com.android.assignment.base.BaseActivity;
import com.android.assignment.search.adapter.SectionsPagerAdapter;
import com.android.assignment.utility.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {
    @BindView(R.id.main_tabPager)
    ViewPager mViewPager;
    private String project_type;
    private String project_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setUp();
    }

    @Override
    protected void setUp() {
        Intent intent = getIntent();
        project_type = intent.getStringExtra(Constants.TYPE);
        project_language = intent.getStringExtra(Constants.LANGUAGE);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setViewAdapter();

    }

    private void setViewAdapter() {
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),project_type,project_language);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

}
