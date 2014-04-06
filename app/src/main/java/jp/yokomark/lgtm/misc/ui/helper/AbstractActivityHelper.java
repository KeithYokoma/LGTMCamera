package jp.yokomark.lgtm.misc.ui.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractActivityHelper {
    private Context mContext;

    public AbstractActivityHelper(Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }

    protected Activity getActivity() {
        return (Activity) mContext;
    }

    @SuppressWarnings("unchecked") // We know that all views should be a child of View.
    protected <T extends View> T findViewById(int id) {
        return (T) getActivity().findViewById(id);
    }
}
