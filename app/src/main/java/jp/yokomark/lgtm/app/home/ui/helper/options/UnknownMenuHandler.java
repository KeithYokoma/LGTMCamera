package jp.yokomark.lgtm.app.home.ui.helper.options;

import android.util.Log;

import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class UnknownMenuHandler implements OptionsMenuHandler<MainActivity> {
    public static final String TAG = UnknownMenuHandler.class.getSimpleName();

    @Override
    public boolean handle(MainActivity activity) {
        Log.e(TAG, "unknown menu has been selected.");
        return false;
    }
}
