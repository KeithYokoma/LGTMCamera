package jp.yokomark.lgtm.app.home.ui.helper.button;

import android.content.Intent;

import jp.yokomark.lgtm.app.history.ui.ComposeHistoryActivity;
import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.misc.ui.helper.ViewClickEventHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShowPictureEventHandler implements ViewClickEventHandler<MainActivity> {
    public static final String TAG = ShowPictureEventHandler.class.getSimpleName();

    @Override
    public void handle(MainActivity activity) {
        Intent intent = new Intent(activity, ComposeHistoryActivity.class);
        activity.startActivity(intent);
    }
}
