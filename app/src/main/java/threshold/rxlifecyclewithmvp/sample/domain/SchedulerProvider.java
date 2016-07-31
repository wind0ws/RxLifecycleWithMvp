package threshold.rxlifecyclewithmvp.sample.domain;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 设置Scheduler
 * Created by Threshold on 2016/2/19.
 */
public interface SchedulerProvider {

    <T> Observable.Transformer<T,T> applySchedulers();

    //下面是这个接口的三个实现.

    SchedulerProvider IONETWORK=new SchedulerProvider() {
        @Override
        public <T> Observable.Transformer<T, T> applySchedulers() {
            return new Observable.Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> tObservable) {
                    return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                }
            };
        }
    };

    SchedulerProvider COMPUTATION=new SchedulerProvider() {
        @Override
        public <T> Observable.Transformer<T, T> applySchedulers() {
            return new Observable.Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> tObservable) {
                    return tObservable.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
                }
            };
        }
    };

    SchedulerProvider BACKGROUND = new SchedulerProvider() {
        @Override
        public <T> Observable.Transformer<T, T> applySchedulers() {
            return new Observable.Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> tObservable) {
                    return tObservable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
                }
            };
        }
    };

  /* 用法  diskCache.getEntity()
            .doOnNext(entity -> memoryCache = entity)
            .compose(SchedulerProvider.IONETWORK.applySchedulers());*/

}