package com.myapp.mygithubapp;

import android.app.Application;
import com.myapp.mygithubapp.component.ApiComponent;
import com.myapp.mygithubapp.component.DaggerApiComponent;
import com.myapp.mygithubapp.module.ApiModule;
import com.myapp.mygithubapp.module.AppModule;
import com.myapp.mygithubapp.network.Api;

public class MyGithubApplication extends Application {

    private ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(Api.BASE_URL))
                .build();
    }

    public ApiComponent getNetComponent() {
        return apiComponent;
    }
}

