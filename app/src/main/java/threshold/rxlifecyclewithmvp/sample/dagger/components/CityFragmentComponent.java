package threshold.rxlifecyclewithmvp.sample.dagger.components;

import dagger.Component;
import threshold.rxlifecyclewithmvp.sample.dagger.annotations.FragmentScope;
import threshold.rxlifecyclewithmvp.sample.dagger.modules.CityModule;
import threshold.rxlifecyclewithmvp.sample.ui.fragment.CityFragment;

/**
 * Created by threshold on 16/7/31.
 */
@FragmentScope
@Component(
        dependencies = {
                AppComponent.class
        },modules ={
        CityModule.class
}
)
public interface CityFragmentComponent {
    void inject(CityFragment fragment);
}
