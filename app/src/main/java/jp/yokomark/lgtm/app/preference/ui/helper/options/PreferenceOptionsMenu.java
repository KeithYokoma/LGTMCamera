package jp.yokomark.lgtm.app.preference.ui.helper.options;

import android.view.MenuItem;

import jp.yokomark.lgtm.app.preference.ui.PreferenceActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public enum PreferenceOptionsMenu {
    HOME(android.R.id.home, new HomeUpHandler()),
    UNKNOWN(-1, new UnknownMenuHandler());

    private final int mId;
    private final OptionsMenuHandler<PreferenceActivity> mHandler;

    private PreferenceOptionsMenu(int id, OptionsMenuHandler<PreferenceActivity> handler) {
        mId = id;
        mHandler = handler;
    }

    public static PreferenceOptionsMenu valueOf(MenuItem item) {
        for (PreferenceOptionsMenu menu : values()) {
            if (menu.mId == item.getItemId()) {
                return menu;
            }
        }
        return UNKNOWN;
    }

    public OptionsMenuHandler<PreferenceActivity> getHandler() {
        return mHandler;
    }
}
