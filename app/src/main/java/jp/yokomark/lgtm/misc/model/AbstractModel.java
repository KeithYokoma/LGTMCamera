package jp.yokomark.lgtm.misc.model;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractModel {
    private Context mContext;

    public AbstractModel(Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }

    protected LoaderManager getLoaderManager() {
        return ((Activity) mContext).getLoaderManager();
    }
}
