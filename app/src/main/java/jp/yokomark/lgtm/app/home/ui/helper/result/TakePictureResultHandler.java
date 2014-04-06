package jp.yokomark.lgtm.app.home.ui.helper.result;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import javax.inject.Inject;

import jp.mixi.compatibility.android.provider.MediaStoreCompat;
import jp.yokomark.lgtm.app.compose.ui.ComposeActivity;
import jp.yokomark.lgtm.app.home.model.TakePicState;
import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.misc.ui.helper.RequestResultHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class TakePictureResultHandler implements RequestResultHandler<MainActivity> {
    public static final String TAG = TakePictureResultHandler.class.getSimpleName();
    @Inject MediaStoreCompat mCompat;
    @Inject TakePicState mPicState;

    @Override
    public void handle(MainActivity activity, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        activity.getGraph().inject(this);
        Uri uri = mCompat.getCapturedPhotoUri(data, mPicState.getPreparedUri());
        Intent intent = new Intent(activity, ComposeActivity.class);
        intent.putExtra(ComposeActivity.EXTRA_URI, uri);
        activity.startActivity(intent);
    }
}
