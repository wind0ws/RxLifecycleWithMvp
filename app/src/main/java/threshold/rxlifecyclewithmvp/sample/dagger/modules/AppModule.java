package threshold.rxlifecyclewithmvp.sample.dagger.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import threshold.rxlifecyclewithmvp.sample.App;
import threshold.rxlifecyclewithmvp.sample.dagger.annotations.ForApp;

/**
 * Module of App.
 * Created by threshold on 16/7/30.
 */
@Module
public class AppModule {

    private App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public App provideApp() {
        return mApp;
    }

    @ForApp
    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }





}
