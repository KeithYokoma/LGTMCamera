package jp.yokomark.lgtm.app.preference.ui;

import android.app.Activity;
import android.os.Bundle;

import jp.yokomark.lgtm.R;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class LicenseListActivity extends Activity {
    public static final String TAG = LicenseListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);
    }
}
