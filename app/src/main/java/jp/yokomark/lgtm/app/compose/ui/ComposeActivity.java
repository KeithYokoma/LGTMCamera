package jp.yokomark.lgtm.app.compose.ui;

import android.app.Activity;
import android.os.Bundle;

import com.amalgam.os.BundleUtils;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComposeActivity extends Activity {
    public static final String TAG = ComposeActivity.class.getSimpleName();
    public static final String EXTRA_URI = BundleUtils.buildKey(ComposeActivity.class, "EXTRA_URI");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
