package jp.yokomark.lgtm.app.preference.ui;

import android.os.Bundle;

import com.anprosit.android.dagger.ActivityModule;
import com.anprosit.android.dagger.ui.DaggerActivity;

import java.util.Arrays;
import java.util.List;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.preference.ui.helper.LicenseModule;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class LicenseListActivity extends DaggerActivity {
    public static final String TAG = LicenseListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.asList(new ActivityModule(this), new LicenseModule());
    }
}
