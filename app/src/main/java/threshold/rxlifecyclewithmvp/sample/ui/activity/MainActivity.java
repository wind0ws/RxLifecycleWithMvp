package threshold.rxlifecyclewithmvp.sample.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import threshold.rxlifecyclewithmvp.sample.App;
import threshold.rxlifecyclewithmvp.sample.R;
import threshold.rxlifecyclewithmvp.sample.dagger.components.DaggerMainComponent;
import threshold.rxlifecyclewithmvp.sample.dagger.modules.MainModule;
import threshold.rxlifecyclewithmvp.sample.ui.fragment.CityFragment;
import threshold.rxlifecyclewithmvp.sample.ui.fragment.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements CityFragment.OnListFragmentInteractionListener {


    @Inject
    FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        initDependency();
        showFragment();
    }

    private void showFragment() {
        mFragmentManager.beginTransaction()
                .replace(R.id.rootContainer,CityFragment.newInstance(1), CityFragment.class.getSimpleName())
                .commit();
    }

    private void initDependency() {
        DaggerMainComponent.builder()
                .appComponent(App.getApp(this).getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Logger.d(item);
    }
}
