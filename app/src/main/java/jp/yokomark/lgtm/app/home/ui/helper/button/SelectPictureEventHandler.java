package jp.yokomark.lgtm.app.home.ui.helper.button;

import com.laevatein.Laevatein;
import com.laevatein.MimeType;

import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.app.home.ui.helper.result.MainRequest;
import jp.yokomark.lgtm.misc.ui.helper.ViewClickEventHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class SelectPictureEventHandler implements ViewClickEventHandler<MainActivity> {
    public static final String TAG = SelectPictureEventHandler.class.getSimpleName();

    @Override
    public void handle(MainActivity activity) {
        Laevatein.from(activity).choose(MimeType.allOf())
                .capture(true)
                .count(0, 1)
                .forResult(MainRequest.SELECT.getCode());
    }
}
