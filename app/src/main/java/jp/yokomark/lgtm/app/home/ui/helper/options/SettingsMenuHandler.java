package jp.yokomark.lgtm.app.home.ui.helper.options;

import android.content.Intent;

import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.app.preference.ui.PreferenceActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class SettingsMenuHandler implements OptionsMenuHandler<MainActivity> {
    @Override
    public boolean handle(MainActivity activity) {
        Intent intent = new Intent(activity, PreferenceActivity.class);
        activity.startActivity(intent);
        return true;
    }
}
