package threshold.rxlifecyclewithmvp.sample.presentation;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import threshold.rxlifecyclewithmvp.sample.ui.viewinterface.CityView;

/**
 * Created by threshold on 16/7/30.
 */
public interface CityPresenter  extends MvpPresenter<CityView> {
    void loadData(boolean isPullRefresh);
}
