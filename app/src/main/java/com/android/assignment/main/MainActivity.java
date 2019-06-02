package com.android.assignment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.assignment.R;
import com.android.assignment.base.BaseActivity;
import com.android.assignment.main.persenter.MainMvpPresenter;
import com.android.assignment.main.view.MainView;
import com.android.assignment.search.SearchActivity;
import com.android.assignment.utility.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainMvpPresenter<MainView> mPresenter;


    @BindView(R.id.edProjectType)
    EditText edProjectType;
    @BindView(R.id.edProjectLanguage)
    EditText edProjectLanguage;
    @BindView(R.id.btnSend)
    Button btnSend;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    private String project_type;
    private String project_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(MainActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setTitle("Search");
    }

    @OnClick(R.id.btnSend)
    public void onViewClicked() {
        project_type = edProjectType.getText().toString();
        project_language = edProjectLanguage.getText().toString();
        mPresenter.checkInputData(project_type, project_language);
        hideKeyboard();

    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        intent.putExtra(Constants.TYPE, project_type);
        intent.putExtra(Constants.LANGUAGE, project_language);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
