package com.android.assignment.di.component;

import com.android.assignment.di.PerActivity;
import com.android.assignment.di.module.ActivityModule;
import com.android.assignment.searchlist.SearchListFragment;
import com.android.assignment.main.MainActivity;
import com.android.assignment.search.SearchActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(SearchActivity activity);
    void inject(SearchListFragment fragment);

}

