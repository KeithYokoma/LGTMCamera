package jp.yokomark.lgtm.app.compose.ui.helper.options;

import android.util.Log;

import jp.yokomark.lgtm.app.compose.ui.ComposeActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class UnknownMenuHandler implements OptionsMenuHandler<ComposeActivity> {
    public static final String TAG = UnknownMenuHandler.class.getSimpleName();

    @Override
    public boolean handle(ComposeActivity activity) {
        Log.e(TAG, "unknown menu has been selected.");
        return false;
    }
}
