package com.android.assignment.main.persenter;

import com.android.assignment.R;
import com.android.assignment.base.BasePresenter;
import com.android.assignment.main.view.MainView;

import javax.inject.Inject;

public class MainMvpPresenter<V extends MainView> extends BasePresenter<V> implements MainPresenter<V> {

    @Inject
    public MainMvpPresenter() {
        super();
    }

    @Override
    public void checkInputData(String project_type, String project_language) {
        if (project_type == null || project_type.isEmpty()) {
            getMvpView().onError(R.string.type_validation);
            return;
        }
        if (project_language == null || project_language.isEmpty()) {
            getMvpView().onError(R.string.language_validation);
            return;
        }


        getMvpView().openMainActivity();
    }
}
