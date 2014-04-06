package jp.yokomark.lgtm.app.home.ui.helper.button;

import android.view.View;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.misc.ui.helper.ViewClickEventHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public enum MainViewClickEvent {
    TAKE(R.id.button_take_pic, new TakePictureEventHandler()),
    SELECT(R.id.button_select_pic, new SelectPictureEventHandler()),
    SHOW(R.id.button_show_pic, new ShowPictureEventHandler());

    private final int mId;
    private final ViewClickEventHandler<MainActivity> mHandler;

    private MainViewClickEvent(int id, ViewClickEventHandler<MainActivity> handler) {
        mId = id;
        mHandler = handler;
    }

    public static MainViewClickEvent valueOf(View view) {
        for (MainViewClickEvent event : values()) {
            if (event.mId == view.getId()) {
                return event;
            }
        }
        throw new IllegalArgumentException("unknown view id: " + view.getId());
    }

    public ViewClickEventHandler<MainActivity> getHandler() {
        return mHandler;
    }
}
