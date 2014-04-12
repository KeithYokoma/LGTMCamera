package jp.yokomark.lgtm;

import android.content.Context;

import com.anprosit.android.dagger.annotation.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.yokomark.lgtm.media.client.MediaStoreClient;
import jp.yokomark.lgtm.media.loader.SaveImageTask;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
@Module(
        injects = {
                Kutan.class,
                SaveImageTask.class
        },
        complete = false,
        library = true
)
public class KutanModule {
    @Provides
    @Singleton
    public MediaStoreClient provideMediaStoreClient(@ForApplication Context context) {
        return new MediaStoreClient(context);
    }
}
