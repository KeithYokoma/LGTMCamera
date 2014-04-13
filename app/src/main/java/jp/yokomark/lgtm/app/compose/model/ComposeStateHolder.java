package jp.yokomark.lgtm.app.compose.model;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;

import com.amalgam.os.BundleUtils;
import com.anprosit.android.dagger.annotation.ForActivity;
import com.anprosit.android.dagger.utils.ObjectGraphUtils;
import com.squareup.otto.Bus;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import jp.yokomark.lgtm.app.compose.entity.ComposeData;
import jp.yokomark.lgtm.app.compose.event.SaveSuccessEvent;
import jp.yokomark.lgtm.media.loader.SaveImageTask;
import jp.yokomark.lgtm.media.utils.ImageUtils;
import jp.yokomark.lgtm.misc.event.EventBusUtils;
import jp.yokomark.lgtm.misc.model.AbstractModel;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComposeStateHolder extends AbstractModel implements LoaderManager.LoaderCallbacks<Uri> {
    public static final String TAG = ComposeStateHolder.class.getSimpleName();
    private static final int LOADER_ID = 1;
    private static final String ARGS_BITMAP = BundleUtils.buildKey(ComposeStateHolder.class, "ARGS_BITMAP");
    private static final String STATE_DATA = BundleUtils.buildKey(ComposeStateHolder.class, "STATE_DATA");
    private static final String STATE_VIEW_DIM = BundleUtils.buildKey(ComposeStateHolder.class, "STATE_VIEW_DIM");
    private static final String STATE_X_SCALE = BundleUtils.buildKey(ComposeStateHolder.class, "STATE_X_SCALE");
    private static final String STATE_Y_SCALE = BundleUtils.buildKey(ComposeStateHolder.class, "STATE_Y_SCALE");
    private ComposeData mData;
    private Point mViewDimension;
    private float mXScaleFactor;
    private float mYScaleFactor;
    @Inject Bus mBus;

    @Inject
    public ComposeStateHolder(@ForActivity Context context) {
        super(context);
        ObjectGraphUtils.getObjectGraph(context).inject(this);
    }

    @Override
    public Loader<Uri> onCreateLoader(int id, Bundle args) {
        Bitmap bitmap = args.getParcelable(ARGS_BITMAP);
        return new SaveImageTask(getContext(), bitmap);
    }

    @Override
    public void onLoadFinished(Loader<Uri> loader, Uri data) {
        getLoaderManager().destroyLoader(LOADER_ID);
        EventBusUtils.postOnMainThread(mBus, new SaveSuccessEvent(data));
    }

    @Override
    public void onLoaderReset(Loader<Uri> loader) {}

    public void onCreate(Uri uri, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mData = ComposeData.buildDefault(getContext(), uri);
            Point raw = ImageUtils.getRawBitmapBound(getContentResolver(), uri);
            mViewDimension = ImageUtils.getViewBitmapBound(getContentResolver(), uri);
            mXScaleFactor = mViewDimension.x / raw.x;
            mYScaleFactor = mViewDimension.y / raw.x;
        } else {
            mData = savedInstanceState.getParcelable(STATE_DATA);
            mViewDimension = savedInstanceState.getParcelable(STATE_VIEW_DIM);
            mXScaleFactor = savedInstanceState.getFloat(STATE_X_SCALE);
            mYScaleFactor = savedInstanceState.getFloat(STATE_Y_SCALE);
        }
    }

    public void onSaveInstanceState(@Nonnull Bundle outState) {
        outState.putParcelable(STATE_DATA, mData);
        outState.putParcelable(STATE_VIEW_DIM, mViewDimension);
        outState.putFloat(STATE_X_SCALE, mXScaleFactor);
        outState.putFloat(STATE_Y_SCALE, mYScaleFactor);
    }

    public void onDestroy() {
        getLoaderManager().destroyLoader(LOADER_ID);
    }

    public @Nonnull ComposeData getData() {
        return mData;
    }

    public void save(@Nonnull Bitmap bitmap) {
        Bundle args = new Bundle();
        args.putParcelable(ARGS_BITMAP, bitmap);
        getLoaderManager().initLoader(LOADER_ID, args, this);
    }
}
