package jp.yokomark.lgtm.app.home.ui.helper.options;

import android.view.MenuItem;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public enum MainOptionsMenu {
    SETTINGS(R.id.action_settings, new SettingsMenuHandler()),
    UNKNOWN(-1, new UnknownMenuHandler());

    private final int mId;
    private final OptionsMenuHandler<MainActivity> mHandler;

    private MainOptionsMenu(int id, OptionsMenuHandler<MainActivity> handler) {
        mId = id;
        mHandler = handler;
    }

    public static MainOptionsMenu valueOf(MenuItem item) {
        for (MainOptionsMenu menu : values()) {
            if (menu.mId == item.getItemId()) {
                return menu;
            }
        }
        return UNKNOWN;
    }

    public OptionsMenuHandler<MainActivity> getHandler() {
        return mHandler;
    }
}
