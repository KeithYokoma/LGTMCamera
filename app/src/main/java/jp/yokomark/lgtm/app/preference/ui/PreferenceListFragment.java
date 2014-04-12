package jp.yokomark.lgtm.app.preference.ui;

import android.os.Bundle;

import com.anprosit.android.dagger.ui.DaggerPreferenceFragment;

import javax.inject.Inject;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.preference.ui.helper.PreferenceListHelper;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class PreferenceListFragment extends DaggerPreferenceFragment {
    public static final String TAG = PreferenceListFragment.class.getSimpleName();
    @Inject PreferenceListHelper mHelper;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_list);
        mHelper.setUpVersionView(this);
        mHelper.setUpLicenseView(this);
    }
}
