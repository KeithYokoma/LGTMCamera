package jp.yokomark.lgtm.app.compose.ui.helper.options;

import jp.yokomark.lgtm.app.compose.ui.ComposeActivity;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class HomeUpHandler implements OptionsMenuHandler<ComposeActivity> {
    @Override
    public boolean handle(ComposeActivity activity) {
        activity.finish();
        return true;
    }
}
