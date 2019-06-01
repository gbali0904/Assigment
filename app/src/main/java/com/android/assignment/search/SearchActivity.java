package com.android.assignment.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.assignment.R;
import com.android.assignment.base.BaseActivity;
import com.android.assignment.detail.SearchDetailFragment;
import com.android.assignment.list.SearchListFragment;
import com.android.assignment.list.adapter.SearchListAdapter;
import com.android.assignment.list.model.ModelForSearchList;
import com.android.assignment.search.adapter.SectionsPagerAdapter;
import com.android.assignment.utility.Constants;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {
    @BindView(R.id.main_tabPager)
    ViewPager mViewPager;
    @BindView(R.id.edProjectType)
    EditText edProjectType;
    @BindView(R.id.edProjectLanguage)
    EditText edProjectLanguage;
    private String project_type;
    private String project_language;
    private long Splash_TIME_DELEY=10000;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setUp();
    }

    @Override
    protected void setUp() {
        Intent intent = getIntent();
        project_type = intent.getStringExtra(Constants.TYPE);
        project_language = intent.getStringExtra(Constants.LANGUAGE);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setupViewPager(mViewPager);
        setupSearchView(mViewPager);
        SearchListAdapter.setOnItemClickListener(new SearchListAdapter.ClickListener() {
            @Override
            public void onItemClick(ModelForSearchList.ItemsBean itemdata) {

                setDetailFragment(itemdata,mViewPager);

            }
        });
    }

    private void setDetailFragment(ModelForSearchList.ItemsBean itemdata, ViewPager viewPager) {

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.addFrag(SearchDetailFragment.newInstance(itemdata), "Detail");
        viewPager.setAdapter(mSectionsPagerAdapter);
    }

    private void setupSearchView(final ViewPager viewPager) {
        TextWatcher textChangeListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                mHandler.removeCallbacksAndMessages(null);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                        mSectionsPagerAdapter.addFrag(SearchListFragment.newInstance(edProjectType.getText().toString(),edProjectLanguage.getText().toString()), "List");
                        viewPager.setAdapter(mSectionsPagerAdapter);
                    }
                }, 5000);
            }
            @Override
            public void afterTextChanged(final Editable s) {
            }

        };

        edProjectType.addTextChangedListener(textChangeListener);
        edProjectLanguage.addTextChangedListener(textChangeListener);
    }



    private void setupViewPager(ViewPager viewPager)
    {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.addFrag(SearchListFragment.newInstance(project_type,project_language), "List");
        viewPager.setAdapter(mSectionsPagerAdapter);
    }



}
