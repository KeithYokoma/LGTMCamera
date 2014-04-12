package jp.yokomark.lgtm.media.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.anprosit.android.dagger.utils.ObjectGraphUtils;

import javax.inject.Inject;

import jp.yokomark.lgtm.media.client.MediaStoreClient;

/**
 * @author KeithYokoma
 * @version 1.0.0
 * @since 1.0.0
 */
public class SaveImageTask extends AsyncTaskLoader<Uri> {
    public static final String TAG = SaveImageTask.class.getSimpleName();
    @Inject MediaStoreClient mClient;
    private final Bitmap mBitmap;

    public SaveImageTask(Context context, Bitmap bitmap) {
        super(context);
        ObjectGraphUtils.getObjectGraph(context.getApplicationContext()).inject(this);
        mBitmap = bitmap;
    }

    @Override
    public Uri loadInBackground() {
        return mClient.save(mBitmap);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
