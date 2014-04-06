package jp.yokomark.lgtm.app.home.ui.helper;

import android.content.Context;
import android.util.Log;

import jp.yokomark.lgtm.misc.ui.helper.AbstractActivityHelper;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class MainActivityHelper extends AbstractActivityHelper {
    public static final String TAG = MainActivityHelper.class.getSimpleName();

    MainActivityHelper(Context context) {
        super(context);
    }

    public void doSome() {
        Log.v(TAG, "unko");
    }
}
