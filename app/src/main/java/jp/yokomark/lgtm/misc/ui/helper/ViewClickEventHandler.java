package jp.yokomark.lgtm.misc.ui.helper;

import android.app.Activity;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ViewClickEventHandler<A extends Activity> {
    public void handle(A activity);
}
