package jp.yokomark.lgtm.app.license.ui.helper.options;

import android.view.MenuItem;

import jp.yokomark.lgtm.app.license.ui.LicenseListActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public enum LicenseListOptionsMenu {
    HOME(android.R.id.home, new HomeUpHandler()),
    UNKNOWN(-1, new UnknownMenuHandler());

    private final int mId;
    private final OptionsMenuHandler<LicenseListActivity> mHandler;

    private LicenseListOptionsMenu(int id, OptionsMenuHandler<LicenseListActivity> handler) {
        mId = id;
        mHandler = handler;
    }

    public static LicenseListOptionsMenu valueOf(MenuItem item) {
        for (LicenseListOptionsMenu menu : values()) {
            if (menu.mId == item.getItemId()) {
                return menu;
            }
        }
        return UNKNOWN;
    }

    public OptionsMenuHandler<LicenseListActivity> getHandler() {
        return mHandler;
    }
}
