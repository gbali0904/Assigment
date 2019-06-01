package com.android.assignment.main.persenter;

import com.android.assignment.base.BasePresenter;
import com.android.assignment.main.view.MainView;

import javax.inject.Inject;

public class MainMvpPresenter<V extends MainView> extends BasePresenter<V> implements MainPresenter<V> {

    @Inject
    public MainMvpPresenter() {
        super();
    }
    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        getMvpView().openLoginActivity();
    }
}
