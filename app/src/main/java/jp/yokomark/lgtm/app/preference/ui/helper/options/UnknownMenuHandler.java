package jp.yokomark.lgtm.app.preference.ui.helper.options;

import android.util.Log;

import jp.yokomark.lgtm.app.preference.ui.PreferenceActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class UnknownMenuHandler implements OptionsMenuHandler<PreferenceActivity> {
    public static final String TAG = UnknownMenuHandler.class.getSimpleName();

    @Override
    public boolean handle(PreferenceActivity activity) {
        Log.e(TAG, "unknown menu has been selected.");
        return false;
    }
}
