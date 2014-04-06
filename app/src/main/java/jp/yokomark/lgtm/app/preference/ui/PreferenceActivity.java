package jp.yokomark.lgtm.app.preference.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.ObjectGraph;
import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.preference.ui.helper.PreferenceListHelper;
import jp.yokomark.lgtm.app.preference.ui.helper.PreferenceModule;
import jp.yokomark.lgtm.app.preference.ui.helper.options.PreferenceOptionsMenu;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class PreferenceActivity extends Activity {
    public static final String TAG = PreferenceActivity.class.getSimpleName();
    @Inject PreferenceListHelper mHelper;
    private ObjectGraph mGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        mGraph = ObjectGraph.create(new PreferenceModule(this));
        mGraph.inject(this);
        mHelper.setUpActionBar(getActionBar());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PreferenceOptionsMenu menu = PreferenceOptionsMenu.valueOf(item);
        return menu.getHandler().handle(this) || super.onOptionsItemSelected(item);
    }

    public ObjectGraph getGraph() {
        return mGraph;
    }
}
