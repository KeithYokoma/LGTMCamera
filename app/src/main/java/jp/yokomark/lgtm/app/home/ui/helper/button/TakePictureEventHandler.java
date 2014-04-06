package jp.yokomark.lgtm.app.home.ui.helper.button;

import javax.inject.Inject;

import jp.mixi.compatibility.android.provider.MediaStoreCompat;
import jp.yokomark.lgtm.app.home.model.TakePicState;
import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.app.home.ui.helper.result.MainRequest;
import jp.yokomark.lgtm.misc.ui.helper.ViewClickEventHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class TakePictureEventHandler implements ViewClickEventHandler<MainActivity> {
    public static final String TAG = TakePictureEventHandler.class.getSimpleName();
    @Inject MediaStoreCompat mCompat;
    @Inject TakePicState mPicState;

    @Override
    public void handle(MainActivity activity) {
        activity.getGraph().inject(this);
        String preparedUri = mCompat.invokeCameraCapture(activity, MainRequest.TAKE.getCode());
        mPicState.setPreparedUri(preparedUri);
    }
}
