package com.myapp.mygithubapp.component;

import com.myapp.mygithubapp.model.commit.CommitInformation;
import com.myapp.mygithubapp.ui.CommitInfoActivity;
import com.myapp.mygithubapp.ui.MainActivity;
import com.myapp.mygithubapp.module.ApiModule;
import com.myapp.mygithubapp.module.AppModule;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {
    void inject(MainActivity activity);
    void inject(CommitInfoActivity activity);
}

