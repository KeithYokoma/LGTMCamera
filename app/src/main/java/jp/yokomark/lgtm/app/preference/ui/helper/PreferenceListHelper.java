package jp.yokomark.lgtm.app.preference.ui.helper;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.amalgam.content.pm.PackageManagerUtils;
import com.anprosit.android.dagger.annotation.ForActivity;

import javax.inject.Inject;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.license.ui.LicenseListActivity;
import jp.yokomark.lgtm.misc.ui.helper.AbstractActivityHelper;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class PreferenceListHelper extends AbstractActivityHelper {
    public static final String TAG = PreferenceListHelper.class.getSimpleName();

    @Inject
    PreferenceListHelper(@ForActivity Context context) {
        super(context);
    }

    public void setUpActionBar(ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    public void setUpVersionView(PreferenceFragment fragment) {
        Preference preference = fragment.findPreference("VERSION");
        if (preference == null) {
            return;
        }
        preference.setSummary(fragment.getString(R.string.label_summary_pref_version,
                PackageManagerUtils.getVersionName(getContext()), PackageManagerUtils.getVersionCode(getContext())));
    }

    public void setUpLicenseView(PreferenceFragment fragment) {
        Preference preference = fragment.findPreference("LICENSE");
        if (preference == null) {
            return;
        }
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(getContext(), LicenseListActivity.class);
                getContext().startActivity(intent);
                return true;
            }
        });
    }
}
