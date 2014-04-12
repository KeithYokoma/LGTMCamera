package jp.yokomark.lgtm.app.preference.ui.helper;

import android.content.Context;

import com.anprosit.android.dagger.annotation.ForActivity;

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
@Module(
        injects = {
                PreferenceActivity.class,
                PreferenceListFragment.class
        },
        complete = false,
        library = true
)
public class PreferenceModule {
    @Provides
    @Singleton
    public PreferenceListHelper providePreferenceListHelper(@ForActivity Context context) {
        return new PreferenceListHelper(context);
    }
}
