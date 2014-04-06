package jp.yokomark.lgtm.app.preference.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.ObjectGraph;
import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.preference.ui.helper.LicenseListHelper;
import jp.yokomark.lgtm.app.preference.ui.helper.LicenseModule;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class LicenseListFragment extends Fragment {
    public static final String TAG = LicenseListFragment.class.getSimpleName();
    @Inject LicenseListHelper mHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_license, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ObjectGraph graph = ObjectGraph.create(new LicenseModule(getActivity()));
        graph.inject(this);
        mHelper.setUpLicenseListView();
    }
}