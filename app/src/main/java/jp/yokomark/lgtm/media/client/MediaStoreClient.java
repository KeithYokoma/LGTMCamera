package jp.yokomark.lgtm.media.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.anprosit.android.dagger.annotation.ForApplication;

import java.io.IOException;

import javax.inject.Inject;

import jp.yokomark.lgtm.utils.StorageUtils;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class MediaStoreClient {
    public static final String TAG = MediaStoreClient.class.getSimpleName();
    private final Context mContext;

    @Inject
    public MediaStoreClient(@ForApplication Context context) {
        mContext = context;
    }

    public Uri save(Bitmap bitmap) {
        try {
            return StorageUtils.saveImage(mContext, bitmap, Bitmap.CompressFormat.JPEG, 100);
        } catch (IOException e) {
            Log.e(TAG, "io error on save image: ", e);
            return null;
        }
    }
}
