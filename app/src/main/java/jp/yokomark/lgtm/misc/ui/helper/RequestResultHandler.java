package jp.yokomark.lgtm.misc.ui.helper;

import android.app.Activity;
import android.content.Intent;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public interface RequestResultHandler<A extends Activity> {
    public void handle(A activity, int resultCode, Intent data);
}
