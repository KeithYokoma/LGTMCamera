package jp.yokomark.lgtm.app.preference.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.anprosit.android.dagger.ActivityModule;
import com.anprosit.android.dagger.ui.DaggerActivity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.preference.ui.helper.PreferenceListHelper;
import jp.yokomark.lgtm.app.preference.ui.helper.PreferenceModule;
import jp.yokomark.lgtm.app.preference.ui.helper.options.PreferenceOptionsMenu;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class PreferenceActivity extends DaggerActivity {
    public static final String TAG = PreferenceActivity.class.getSimpleName();
    @Inject PreferenceListHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        mHelper.setUpActionBar(getActionBar());
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.asList(new ActivityModule(this), new PreferenceModule());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PreferenceOptionsMenu menu = PreferenceOptionsMenu.valueOf(item);
        return menu.getHandler().handle(this) || super.onOptionsItemSelected(item);
    }
}
