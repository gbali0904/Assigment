package com.android.assignment.main.persenter;

import com.android.assignment.base.MvpPresenter;
import com.android.assignment.di.PerActivity;
import com.android.assignment.main.view.MainView;

@PerActivity
public interface MainPresenter<V extends MainView> extends MvpPresenter<V> {
    void onAttach(V mvpView);
}
