package jp.yokomark.lgtm.app.history.ui.helper.options;

import jp.yokomark.lgtm.app.history.ui.ComposeHistoryActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class HomeUpHandler implements OptionsMenuHandler<ComposeHistoryActivity> {
    public static final String TAG = HomeUpHandler.class.getSimpleName();

    @Override
    public boolean handle(ComposeHistoryActivity activity) {
        activity.finish();
        return true;
    }
}
