package jp.yokomark.lgtm.app.license.ui.helper.options;

import android.util.Log;

import jp.yokomark.lgtm.app.license.ui.LicenseListActivity;
import jp.yokomark.lgtm.app.preference.ui.PreferenceActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class UnknownMenuHandler implements OptionsMenuHandler<LicenseListActivity> {
    public static final String TAG = UnknownMenuHandler.class.getSimpleName();

    @Override
    public boolean handle(LicenseListActivity activity) {
        Log.e(TAG, "unknown menu has been selected.");
        return false;
    }
}
