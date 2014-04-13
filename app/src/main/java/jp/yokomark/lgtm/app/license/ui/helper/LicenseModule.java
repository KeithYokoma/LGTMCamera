package jp.yokomark.lgtm.app.license.ui.helper;

import android.content.Context;

import com.anprosit.android.dagger.annotation.ForActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.yokomark.lgtm.app.license.ui.LicenseListActivity;
import jp.yokomark.lgtm.app.license.ui.LicenseListFragment;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
@Module(
        injects = {
                LicenseListActivity.class,
                LicenseListFragment.class
        },
        complete = false,
        library = true
)
public class LicenseModule {
    @Provides
    @Singleton
    public LicenseListHelper provideLicenseListHelper(@ForActivity Context context) {
        return new LicenseListHelper(context);
    }
}
