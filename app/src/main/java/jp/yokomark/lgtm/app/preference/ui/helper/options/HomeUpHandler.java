package jp.yokomark.lgtm.app.preference.ui.helper.options;

import jp.yokomark.lgtm.app.preference.ui.PreferenceActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class HomeUpHandler implements OptionsMenuHandler<PreferenceActivity> {
    public static final String TAG = HomeUpHandler.class.getSimpleName();

    @Override
    public boolean handle(PreferenceActivity activity) {
        activity.finish();
        return true;
    }
}
