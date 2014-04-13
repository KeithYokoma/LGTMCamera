package jp.yokomark.lgtm.app.compose.ui.helper.options;

import javax.inject.Inject;

import jp.yokomark.lgtm.app.compose.ui.ComposeActivity;
import jp.yokomark.lgtm.app.compose.ui.helper.ComposeViewHelper;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class SaveHandler implements OptionsMenuHandler<ComposeActivity> {
    @Inject ComposeViewHelper mHelper;

    @Override
    public boolean handle(ComposeActivity activity) {
        activity.inject(this);
        return true;
    }
}
