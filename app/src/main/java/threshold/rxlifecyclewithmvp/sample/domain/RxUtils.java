package threshold.rxlifecyclewithmvp.sample.domain;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 *
 * Created by Threshold on 2016/2/25.
 */
public class RxUtils {

    public static void unsubscribeIfNotNull(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public static CompositeSubscription getNewCompositeSubIfUnsubscribed(CompositeSubscription subscription) {
        if (subscription == null || subscription.isUnsubscribed()) {
            return new CompositeSubscription();
        }
        return subscription;
    }
}