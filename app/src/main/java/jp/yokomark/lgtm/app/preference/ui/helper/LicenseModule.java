package jp.yokomark.lgtm.app.preference.ui.helper;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.yokomark.lgtm.app.preference.ui.LicenseListActivity;
import jp.yokomark.lgtm.app.preference.ui.LicenseListFragment;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
@Module(injects = {
        LicenseListActivity.class,
        LicenseListFragment.class,
})
public class LicenseModule {
    Context mContext;

    public LicenseModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public LicenseListHelper provideLicenseListHelper() {
        return new LicenseListHelper(mContext);
    }
}
