package jp.yokomark.lgtm.app.compose.model;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;

import com.amalgam.os.BundleUtils;
import com.anprosit.android.dagger.annotation.ForActivity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import jp.yokomark.lgtm.app.compose.entity.ComposeData;
import jp.yokomark.lgtm.media.utils.ImageUtils;
import jp.yokomark.lgtm.misc.model.AbstractModel;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComposeStateHolder extends AbstractModel {
    public static final String TAG = ComposeStateHolder.class.getSimpleName();
    private static final String STATE_DATA = BundleUtils.buildKey(ComposeStateHolder.class, "STATE_DATA");
    private static final String STATE_VIEW_DIM = BundleUtils.buildKey(ComposeStateHolder.class, "STATE_VIEW_DIM");
    private static final String STATE_X_SCALE = BundleUtils.buildKey(ComposeStateHolder.class, "STATE_X_SCALE");
    private static final String STATE_Y_SCALE = BundleUtils.buildKey(ComposeStateHolder.class, "STATE_Y_SCALE");
    private ComposeData mData;
    private Point mViewDimension;
    private float mXScaleFactor;
    private float mYScaleFactor;

    @Inject
    public ComposeStateHolder(@ForActivity Context context) {
        super(context);
    }

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

    public @Nonnull Point getImageViewDimension() {
        return mViewDimension;
    }

    public @Nonnull ComposeData getData() {
        return mData;
    }
}
