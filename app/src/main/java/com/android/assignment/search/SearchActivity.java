package com.android.assignment.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.android.assignment.R;
import com.android.assignment.base.BaseActivity;
import com.android.assignment.search.adapter.SectionsPagerAdapter;
import com.android.assignment.search.persenter.SearchMVPPersenter;
import com.android.assignment.search.view.SearchView;
import com.android.assignment.searchdetail.SearchDetailFragment;
import com.android.assignment.searchlist.SearchListFragment;
import com.android.assignment.searchlist.adapter.SearchListAdapter;
import com.android.assignment.searchlist.model.ModelForSearchList;
import com.android.assignment.utility.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements SearchView, SearchListAdapter.ClickListener {
    @BindView(R.id.main_tabPager)
    ViewPager mViewPager;
    @BindView(R.id.edProjectType)
    EditText edProjectType;
    @BindView(R.id.edProjectLanguage)
    EditText edProjectLanguage;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    private String project_type;
    private String project_language;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    @Inject
    SearchMVPPersenter<SearchView> mPresenter;

    @Inject
    SectionsPagerAdapter mSectionsPagerAdapter;

    @Inject
    SearchListAdapter searchListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SearchActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        Intent intent = getIntent();
        project_type = intent.getStringExtra(Constants.TYPE);
        project_language = intent.getStringExtra(Constants.LANGUAGE);
        setupViewPager();
        setupSearchView();
        searchListAdapter.setOnItemClickListener(this);
        tabLayout.setupWithViewPager(mViewPager);
    }


    private void setupSearchView() {
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

                        mSectionsPagerAdapter.hideDetails();
                        mSectionsPagerAdapter.showSearchList(edProjectType.getText()
                                .toString(), edProjectLanguage.getText().toString());
                        hideKeyboard();
                    }
                }, 1000);
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }

        };

        edProjectType.addTextChangedListener(textChangeListener);
        edProjectLanguage.addTextChangedListener(textChangeListener);
    }


    private void setupViewPager() {
        mSectionsPagerAdapter.showSearchList(project_type, project_language);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    private void setDetailFragment(ModelForSearchList.ItemsBean itemdata) {
        mSectionsPagerAdapter.showDetails(itemdata);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }


    @Override
    public void onItemClick(ModelForSearchList.ItemsBean id) {
        setDetailFragment(id);
        mViewPager.setCurrentItem(2);
    }

    @Override
    public void onHide() {
        mSectionsPagerAdapter.hideDetails();
    }


}
