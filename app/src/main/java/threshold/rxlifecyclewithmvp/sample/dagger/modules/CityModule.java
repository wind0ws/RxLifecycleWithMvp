package threshold.rxlifecyclewithmvp.sample.dagger.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import threshold.rxlifecyclewithmvp.sample.dagger.annotations.FragmentScope;
import threshold.rxlifecyclewithmvp.sample.domain.ErrorMessageDeterminer;
import threshold.rxlifecyclewithmvp.sample.presentation.CityPresenter;
import threshold.rxlifecyclewithmvp.sample.presentation.impl.CityPresenterImpl;

/**
 * Created by threshold on 16/7/31.
 */
@Module
public class CityModule {

    private Context mContext;

    public CityModule(Context context) {
        mContext = context;
    }

    @Provides
    @FragmentScope
    public ErrorMessageDeterminer provideErrorMessgae() {
        return new ErrorMessageDeterminer(mContext);
    }

    @Provides
    @FragmentScope
    public CityPresenter providePresenter() {
        return new CityPresenterImpl();
    }
}
