package jp.yokomark.lgtm.app.compose.ui.helper;

import android.content.Context;

import com.anprosit.android.dagger.annotation.ForActivity;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.yokomark.lgtm.app.compose.model.ComposeStateHolder;
import jp.yokomark.lgtm.app.compose.ui.ComposeActivity;
import jp.yokomark.lgtm.app.compose.ui.helper.options.SaveHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
@Module(
        injects = {
                ComposeActivity.class,
                ComposeStateHolder.class,
                SaveHandler.class
        },
        complete = false,
        library = true
)
public class ComposeModule {
    @Provides
    @Singleton
    public ComposeStateHolder provideComposeStateHolder(@ForActivity Context context) {
        return new ComposeStateHolder(context);
    }

    @Provides
    @Singleton
    public ComposeViewHelper provideComposeViewHelper(@ForActivity Context context) {
        return new ComposeViewHelper(context);
    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }
}
