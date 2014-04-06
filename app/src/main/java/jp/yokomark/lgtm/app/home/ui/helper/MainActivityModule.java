package jp.yokomark.lgtm.app.home.ui.helper;

import android.content.Context;

import com.amalgam.os.HandlerUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.mixi.compatibility.android.provider.MediaStoreCompat;
import jp.yokomark.lgtm.app.home.model.TakePicState;
import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.app.home.ui.helper.button.SelectPictureEventHandler;
import jp.yokomark.lgtm.app.home.ui.helper.button.ShowPictureEventHandler;
import jp.yokomark.lgtm.app.home.ui.helper.button.TakePictureEventHandler;
import jp.yokomark.lgtm.app.home.ui.helper.result.SelectPictureResultHandler;
import jp.yokomark.lgtm.app.home.ui.helper.result.TakePictureResultHandler;
import jp.yokomark.lgtm.app.home.ui.helper.result.UnknownResultHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
@Module( injects = {
        MainActivity.class,
        TakePictureEventHandler.class,
        SelectPictureEventHandler.class,
        ShowPictureEventHandler.class,
        TakePictureResultHandler.class,
        SelectPictureResultHandler.class,
        UnknownResultHandler.class
} )
public class MainActivityModule {
    Context mContext;

    public MainActivityModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public MainActivityHelper provideMainActivityHelper() {
        return new MainActivityHelper(mContext);
    }

    @Provides
    @Singleton
    public MediaStoreCompat provideMediaStoreCompat() {
        return new MediaStoreCompat(mContext, HandlerUtils.getMainHandler());
    }

    @Provides
    @Singleton
    public TakePicState provideTakePicState() {
        return new TakePicState();
    }
}
