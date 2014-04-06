package jp.yokomark.lgtm.app.preference.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import javax.inject.Inject;

import dagger.ObjectGraph;
import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.preference.ui.helper.PreferenceListHelper;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class PreferenceListFragment extends PreferenceFragment {
    public static final String TAG = PreferenceListFragment.class.getSimpleName();
    @Inject PreferenceListHelper mHelper;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_list);
        ObjectGraph graph = ((PreferenceActivity) getActivity()).getGraph();
        graph.inject(this);
        mHelper.setUpVersionView(this);
        mHelper.setUpLicenseView(this);
    }
}
