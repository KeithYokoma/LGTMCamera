package jp.yokomark.lgtm.app.license.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.anprosit.android.dagger.ActivityModule;
import com.anprosit.android.dagger.ui.DaggerActivity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.license.ui.helper.LicenseListHelper;
import jp.yokomark.lgtm.app.license.ui.helper.LicenseModule;
import jp.yokomark.lgtm.app.license.ui.helper.options.LicenseListOptionsMenu;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class LicenseListActivity extends DaggerActivity {
    public static final String TAG = LicenseListActivity.class.getSimpleName();
    @Inject LicenseListHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);
        mHelper.setUpActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LicenseListOptionsMenu menu = LicenseListOptionsMenu.valueOf(item);
        return menu.getHandler().handle(this) || super.onOptionsItemSelected(item);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.asList(new ActivityModule(this), new LicenseModule());
    }
}
