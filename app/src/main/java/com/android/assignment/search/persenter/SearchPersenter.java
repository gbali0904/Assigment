package com.android.assignment.search.persenter;

import com.android.assignment.base.MvpPresenter;
import com.android.assignment.base.MvpView;
import com.android.assignment.di.PerActivity;

@PerActivity
public interface SearchPersenter<V extends MvpView> extends MvpPresenter<V> {
}
