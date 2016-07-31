package threshold.rxlifecyclewithmvp.sample.dagger.components;

import dagger.Component;
import threshold.rxlifecyclewithmvp.sample.dagger.annotations.ActivityScope;
import threshold.rxlifecyclewithmvp.sample.dagger.modules.MainModule;
import threshold.rxlifecyclewithmvp.sample.ui.activity.MainActivity;

/**
 * Created by threshold on 16/7/30.
 */
@ActivityScope
@Component(modules = {
        MainModule.class
},dependencies = {
        AppComponent.class
})
public interface MainComponent {
    void inject(MainActivity activity);
}
