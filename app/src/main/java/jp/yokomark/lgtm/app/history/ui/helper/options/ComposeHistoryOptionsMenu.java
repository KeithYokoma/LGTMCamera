package jp.yokomark.lgtm.app.history.ui.helper.options;

import android.view.MenuItem;

import jp.yokomark.lgtm.app.history.ui.ComposeHistoryActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public enum ComposeHistoryOptionsMenu {
    HOME(android.R.id.home, new HomeUpHandler()),
    UNKNOWN(-1, new UnknownMenuHandler());

    private final int mMenuId;
    private final OptionsMenuHandler<ComposeHistoryActivity> mHandler;

    private ComposeHistoryOptionsMenu(int menuId, OptionsMenuHandler<ComposeHistoryActivity> handler) {
        mMenuId = menuId;
        mHandler = handler;
    }

    public static ComposeHistoryOptionsMenu valueOf(MenuItem item) {
        for (ComposeHistoryOptionsMenu menu : values()) {
            if (menu.mMenuId == item.getItemId()) {
                return menu;
            }
        }
        return UNKNOWN;
    }

    public OptionsMenuHandler<ComposeHistoryActivity> getHandler() {
        return mHandler;
    }
}
