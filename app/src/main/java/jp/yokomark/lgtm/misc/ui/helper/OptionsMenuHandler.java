package jp.yokomark.lgtm.misc.ui.helper;

import android.app.Activity;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public interface OptionsMenuHandler<A extends Activity> {
    public boolean handle(A activity);
}