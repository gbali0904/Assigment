package com.android.assignment.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.android.assignment.api.RetrofitImp;
import com.android.assignment.di.ActivityContext;
import com.android.assignment.di.PerActivity;
import com.android.assignment.search.adapter.SectionsPagerAdapter;
import com.android.assignment.searchlist.adapter.SearchListAdapter;
import com.android.assignment.searchlist.persenter.SearchListMvpPresenter;
import com.android.assignment.searchlist.persenter.SearchListPresenter;
import com.android.assignment.searchlist.view.SearchListView;
import com.android.assignment.main.persenter.MainMvpPresenter;
import com.android.assignment.main.persenter.MainPresenter;
import com.android.assignment.main.view.MainView;
import com.android.assignment.search.persenter.SearchMVPPersenter;
import com.android.assignment.search.persenter.SearchPersenter;
import com.android.assignment.search.view.SearchView;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }


    @Provides
    @PerActivity
    MainPresenter<MainView> provideMainPresenter(
            MainMvpPresenter<MainView> presenter) {
        return presenter;
    }
    @Provides
    @PerActivity
    SearchListPresenter<SearchListView> provideSearchListPresenter(
            SearchListMvpPresenter<SearchListView> presenter) {
        return presenter;
    }
    @Provides
    @PerActivity
    SearchPersenter<SearchView> provideSearchPresenter(
            SearchMVPPersenter<SearchView> presenter) {
        return presenter;
    }

    @Provides
    SectionsPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new SectionsPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    SearchListAdapter provideSearchAdapter() {
        return new SearchListAdapter();
    }

    @Provides
    RetrofitImp provideretrofit() {
        return new RetrofitImp();
    }



}