package android.marshon.likequanmintv.librarys.utils;

import java.util.Iterator;
import java.util.LinkedList;

import rx.Subscription;

/**
 * Created by It.Marshon on 2016/11/10 0010 17:50
 */

public class RxUtil {

    public static void cancelSubscriptions(LinkedList<Subscription> subscriptions) {
        Iterator<Subscription> iterator =
                subscriptions.iterator();

        while (iterator.hasNext()){
            Subscription subscription = iterator.next();
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }
}
