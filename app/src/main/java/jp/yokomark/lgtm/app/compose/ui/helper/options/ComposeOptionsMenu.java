package jp.yokomark.lgtm.app.compose.ui.helper.options;

import android.view.MenuItem;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.compose.ui.ComposeActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public enum ComposeOptionsMenu {
    HOME(android.R.id.home, new HomeUpHandler()),
    SAVE(R.id.action_save, new SaveHandler()),
    UNKNOWN(-1, new UnknownMenuHandler());

    private final int mId;
    private final OptionsMenuHandler<ComposeActivity> mHandler;

    private ComposeOptionsMenu(int id, OptionsMenuHandler<ComposeActivity> handler) {
        mId = id;
        mHandler = handler;
    }

    public static ComposeOptionsMenu valueOf(MenuItem item) {
        for (ComposeOptionsMenu menu : values()) {
            if (menu.mId == item.getItemId()) {
                return menu;
            }
        }
        return UNKNOWN;
    }

    public OptionsMenuHandler<ComposeActivity> getHandler() {
        return mHandler;
    }
}
