package jp.yokomark.lgtm.app.preference.ui.helper;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.yokomark.lgtm.app.preference.ui.PreferenceActivity;
import jp.yokomark.lgtm.app.preference.ui.PreferenceListFragment;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
@Module( injects = {
        PreferenceActivity.class,
        PreferenceListFragment.class
})
public class PreferenceModule {
    Context mContext;

    public PreferenceModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public PreferenceListHelper providePreferenceListHelper() {
        return new PreferenceListHelper(mContext);
    }
}
