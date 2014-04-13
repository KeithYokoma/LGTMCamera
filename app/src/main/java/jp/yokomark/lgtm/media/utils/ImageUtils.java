package jp.yokomark.lgtm.media.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.amalgam.database.CursorUtils;
import com.amalgam.io.CloseableUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jp.mixi.compatibility.android.media.ExifInterfaceCompat;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public final class ImageUtils {
    public static final String TAG = ImageUtils.class.getSimpleName();
    private static final int MAX_WIDTH_ON_VIEW = 1600;
    private static final String SCHEME_CONTENT = "content";

    private ImageUtils() {
        throw new AssertionError();
    }

    public static Point getRawBitmapBound(ContentResolver resolver, Uri uri) {
        InputStream is = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            is = resolver.openInputStream(uri);
            BitmapFactory.decodeStream(is, null, options);
            int width = options.outWidth;
            int height = options.outHeight;
            return new Point(width, height);
        } catch (FileNotFoundException e) {
            return new Point(0, 0);
        } finally {
            CloseableUtils.close(is);
        }
    }

    public static String getPath(ContentResolver resolver, Uri uri) {
        if (uri == null) {
            return null;
        }

        if (SCHEME_CONTENT.equals(uri.getScheme())) {
            Cursor cursor = null;
            try {
                cursor = resolver.query(uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null);
                if (cursor == null || !cursor.moveToFirst()) {
                    return null;
                }
                return cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
            } finally {
                CursorUtils.close(cursor);
            }
        }
        return uri.getPath();
    }

    public static boolean shouldRotate(ContentResolver resolver, Uri uri) {
        ExifInterface exif;
        try {
            exif = ExifInterfaceCompat.newInstance(getPath(resolver, uri));
        } catch (IOException e) {
            Log.e(TAG, "could not read exif info of the image: " + uri);
            return false;
        }
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
        return orientation == ExifInterface.ORIENTATION_ROTATE_90
                || orientation == ExifInterface.ORIENTATION_ROTATE_270;
    }

    public static Point getViewBitmapBound(ContentResolver resolver, Uri uri) {
        Point imageSize = getRawBitmapBound(resolver, uri);
        int w = imageSize.x;
        int h = imageSize.y;
        if (shouldRotate(resolver, uri)) {
            w = imageSize.y;
            h = imageSize.x;
        }
        int width = w > MAX_WIDTH_ON_VIEW ? MAX_WIDTH_ON_VIEW : w;
        int height = (int) Math.floor(h * width / width);
        return new Point(width, height);
    }
}
