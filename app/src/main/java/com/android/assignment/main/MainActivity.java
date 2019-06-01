package com.android.assignment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.android.assignment.R;
import com.android.assignment.base.BaseActivity;
import com.android.assignment.main.persenter.MainPresenter;
import com.android.assignment.main.view.MainView;
import com.android.assignment.search.SearchActivity;
import com.android.assignment.utility.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter<MainView> mPresenter;


    @BindView(R.id.edProjectType)
    EditText edProjectType;
    @BindView(R.id.edProjectLanguage)
    EditText edProjectLanguage;
    @BindView(R.id.btnSend)
    Button btnSend;
    private String project_type;
    private String project_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
    }

    @Override
    protected void setUp() { }

    @OnClick(R.id.btnSend)
    public void onViewClicked() {
         project_type = edProjectType.getText().toString();
         project_language = edProjectLanguage.getText().toString();
        if(TextUtils.isEmpty(project_type)) {
            edProjectType.setError(getResources().getString(R.string.type_validation));
            return;
        }else if(TextUtils.isEmpty(project_language)) {
            edProjectLanguage.setError(getResources().getString(R.string.language_validation));
            return;
        } else {
            mPresenter.onAttach(MainActivity.this);
        }

    }

    @Override
    public void openLoginActivity() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        intent.putExtra(Constants.TYPE,project_type);
        intent.putExtra(Constants.LANGUAGE,project_language);
        startActivity(intent);
        finish();
    }
}