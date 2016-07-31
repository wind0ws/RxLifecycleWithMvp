package threshold.rxlifecyclewithmvp.sample;

import android.app.Application;
import android.content.Context;

import threshold.rxlifecyclewithmvp.sample.dagger.components.AppComponent;
import threshold.rxlifecyclewithmvp.sample.dagger.components.DaggerAppComponent;
import threshold.rxlifecyclewithmvp.sample.dagger.modules.AppModule;

/**
 * Created by threshold on 16/7/30.
 */
public class App extends Application {

    public static App getApp(Context context){
        return (App) context.getApplicationContext();
    }

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDependency();
    }

    private void initDependency() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
