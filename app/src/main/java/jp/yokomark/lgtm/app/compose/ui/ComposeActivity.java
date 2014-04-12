package jp.yokomark.lgtm.app.compose.ui;

import android.os.Bundle;

import com.amalgam.os.BundleUtils;
import com.anprosit.android.dagger.ActivityModule;
import com.anprosit.android.dagger.ui.DaggerActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComposeActivity extends DaggerActivity {
    public static final String TAG = ComposeActivity.class.getSimpleName();
    public static final String EXTRA_URI = BundleUtils.buildKey(ComposeActivity.class, "EXTRA_URI");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ActivityModule(this));
    }
}
