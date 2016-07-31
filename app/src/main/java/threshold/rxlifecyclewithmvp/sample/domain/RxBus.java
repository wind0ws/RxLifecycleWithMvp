package threshold.rxlifecyclewithmvp.sample.domain;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * RxBus ,获取实例后post事件
 * Created by Threshold on 2016/2/25.
 */
public class RxBus {
    private static volatile RxBus defaultInstance;
    // 主题
    private final Subject<Object,Object> bus;
    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }
    // 单例RxBus
    public static RxBus getDefault() {
        RxBus rxBus = defaultInstance;
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                rxBus = defaultInstance;
                if (defaultInstance == null) {
                    rxBus = new RxBus();
                    defaultInstance = rxBus;
                }
            }
        }
        return rxBus;
    }
    // 提供了一个新的事件
    public void post (Object o) {
        bus.onNext(o);
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }

    public Observable<Object> toObservable() {
        return bus;
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable(final Class<T> eventType) {
        return bus.ofType(eventType);
    }
}