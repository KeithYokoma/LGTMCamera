package jp.yokomark.lgtm.misc.event;

import com.amalgam.os.HandlerUtils;
import com.squareup.otto.Bus;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public final class EventBusUtils {
    private EventBusUtils() {
        throw new AssertionError();
    }

    public static void postOnMainThread(final Bus bus, final Object event) {
        HandlerUtils.postOnMain(new Runnable() {
            @Override
            public void run() {
                bus.post(event);
            }
        });
    }

    public static void postOnMainThreadWithDelay(final Bus bus, final Object event, long delayInMillis) {
        HandlerUtils.postOnMainWithDelay(new Runnable() {
            @Override
            public void run() {
                bus.post(event);
            }
        }, delayInMillis);
    }
}
