package jp.yokomark.lgtm.app.home.model;

import android.os.Bundle;

import com.amalgam.os.BundleUtils;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class TakePicState {
    public static final String TAG = TakePicState.class.getSimpleName();
    private static final String STATE_PREPARED_URI = BundleUtils.buildKey(TakePicState.class, "STATE_PREPARED_URI");
    private String mPreparedUri;

    public void setPreparedUri(String preparedUri) {
        mPreparedUri = preparedUri;
    }

    public String getPreparedUri() {
        return mPreparedUri;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        mPreparedUri = savedInstanceState.getString(STATE_PREPARED_URI);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_PREPARED_URI, mPreparedUri);
    }
}