package jp.yokomark.lgtm.app.home.ui.helper;

import android.content.Context;
import android.util.Log;

import com.anprosit.android.dagger.annotation.ForActivity;

import javax.inject.Inject;

import jp.yokomark.lgtm.misc.ui.helper.AbstractActivityHelper;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class MainActivityHelper extends AbstractActivityHelper {
    public static final String TAG = MainActivityHelper.class.getSimpleName();

    @Inject
    MainActivityHelper(@ForActivity Context context) {
        super(context);
    }

    public void doSome() {
        Log.v(TAG, "unko");
    }
}
