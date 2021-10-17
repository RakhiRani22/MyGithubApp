package com.myapp.mygithubapp.component;

import com.myapp.mygithubapp.MainActivity;
import com.myapp.mygithubapp.module.ApiModule;
import com.myapp.mygithubapp.module.AppModule;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {
    void inject(MainActivity activity);
}

