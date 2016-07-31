package threshold.rxlifecyclewithmvp.sample.dagger.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import threshold.rxlifecyclewithmvp.sample.App;
import threshold.rxlifecyclewithmvp.sample.dagger.annotations.ForApp;
import threshold.rxlifecyclewithmvp.sample.dagger.modules.AppModule;

/**
 * Created by threshold on 16/7/30.
 */
@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent {
    //向外暴露App和Context
    App getApp();
    @ForApp
    Context appContext();
    void inject(App app);
}
