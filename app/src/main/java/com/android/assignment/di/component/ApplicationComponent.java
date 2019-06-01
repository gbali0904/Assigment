package com.android.assignment.di.component;

import android.content.Context;


import com.android.assignment.app.AppController;
import com.android.assignment.di.ApplicationContext;
import com.android.assignment.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(AppController appController);
    @ApplicationContext
    Context context();
}

