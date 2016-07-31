package threshold.rxlifecyclewithmvp.sample.dagger.modules;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import threshold.rxlifecyclewithmvp.sample.dagger.annotations.ActivityScope;

/**
 * Module of MainActivity
 * Created by threshold on 16/7/30.
 */
@Module
public class MainModule {

    private AppCompatActivity mCompatActivity;

    public MainModule(AppCompatActivity activity) {
        this.mCompatActivity = activity;
    }

    @Provides
    @ActivityScope
    public FragmentManager provideFragmentManager(){
        return mCompatActivity.getSupportFragmentManager();
    }




}
