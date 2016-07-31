package threshold.rxlifecyclewithmvp.sample.presentation.impl;

import java.net.SocketTimeoutException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import threshold.rxlifecyclewithmvp.sample.presentation.CityPresenter;
import threshold.rxlifecyclewithmvp.sample.presentation.RxMvpLcePresenter;
import threshold.rxlifecyclewithmvp.sample.ui.fragment.dummy.DummyContent;
import threshold.rxlifecyclewithmvp.sample.ui.viewinterface.CityView;

/**
 * Created by threshold on 16/7/30.
 */
public class CityPresenterImpl extends RxMvpLcePresenter<CityView,List<DummyContent.DummyItem>> implements CityPresenter{

    private int testCounter =0;

    @Override
    public void loadData(boolean isPullRefresh) {
        Observable<List<DummyContent.DummyItem>> listObservable = Observable.create(new Observable.OnSubscribe<List<DummyContent.DummyItem>>() {
            @Override
            public void call(Subscriber<? super List<DummyContent.DummyItem>> subscriber) {
                subscriber.onStart();
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testCounter++;
                if (testCounter % 2 == 0) {
                    subscriber.onError(new SocketTimeoutException("Error"));
                } else {
                    subscriber.onNext(DummyContent.ITEMS);
                }
                subscriber.onCompleted();
            }
        });
        subscribe(listObservable,isPullRefresh);
    }

}
