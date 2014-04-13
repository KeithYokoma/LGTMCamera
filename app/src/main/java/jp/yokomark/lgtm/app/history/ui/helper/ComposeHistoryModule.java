package jp.yokomark.lgtm.app.history.ui.helper;

import android.content.Context;

import com.anprosit.android.dagger.annotation.ForActivity;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.yokomark.lgtm.app.history.model.ComposeHistoryCollection;
import jp.yokomark.lgtm.app.history.ui.ComposeHistoryActivity;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
@Module(
        injects = {
                ComposeHistoryActivity.class,
                ComposeHistoryCollection.class,
                ComposeHistoryViewHelper.class
        },
        complete = false,
        library = true)
public class ComposeHistoryModule {
    @Provides
    @Singleton
    public ComposeHistoryCollection provideComposeHistoryCollection(@ForActivity Context context) {
        return new ComposeHistoryCollection(context);
    }

    @Provides
    @Singleton
    public ComposeHistoryViewHelper provideComposeHistoryViewHelper(@ForActivity Context context) {
        return new ComposeHistoryViewHelper(context);
    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }
}
