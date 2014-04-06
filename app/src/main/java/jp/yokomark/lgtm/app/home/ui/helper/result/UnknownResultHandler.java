package jp.yokomark.lgtm.app.home.ui.helper.result;

import android.content.Intent;
import android.util.Log;

import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.misc.ui.helper.RequestResultHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class UnknownResultHandler implements RequestResultHandler<MainActivity> {
    public static final String TAG = UnknownResultHandler.class.getSimpleName();

    @Override
    public void handle(MainActivity activity, int resultCode, Intent data) {
        Log.e(TAG, "unknown result received with code: " + resultCode);
    }
}
