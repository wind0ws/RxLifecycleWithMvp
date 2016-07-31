package threshold.rxlifecyclewithmvp.sample.ui.viewinterface;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import threshold.rxlifecyclewithmvp.sample.ui.fragment.dummy.DummyContent;

/**
 * Created by threshold on 16/7/30.
 */
public interface CityView extends MvpLceView<List<DummyContent.DummyItem>> {
}
