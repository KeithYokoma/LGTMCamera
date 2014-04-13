package jp.yokomark.lgtm.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.amalgam.io.CloseableUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public final class StorageUtils {
    public static final String TAG = StorageUtils.class.getSimpleName();
    public static final String BUCKET_NAME = "LGTMCamera";

    private StorageUtils() {
        throw new AssertionError();
    }

    public static Uri saveImage(Context context, Bitmap bitmap, Bitmap.CompressFormat format, int quality) throws IOException {
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), BUCKET_NAME);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("cannot create temporary dir on the external storage");
            }
        }
        File dest = new File(dir, String.valueOf(System.currentTimeMillis()) + ".jpg");
        Log.v(TAG, "saving as: " + dest.getAbsolutePath());
        if (!saveAsFile(dest, bitmap, format, quality)) {
            throw new IOException("failed to save bitmap as a file.");
        }
        String url = MediaStore.Images.Media.insertImage(context.getContentResolver(), dest.getAbsolutePath(), "", "");
        Log.v(TAG, "stored at: " + url);
        MediaScannerConnection.scanFile(context, new String[] {dest.getAbsolutePath()}, new String[] { "image/jpeg" }, null);
        return Uri.parse(url);
    }

    public static boolean saveAsFile(File file, Bitmap bitmap, Bitmap.CompressFormat format, int quality) throws IOException {
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            if (!bitmap.compress(format, quality, stream)) {
                throw new IOException("failed to compress bitmap to the destination: " + file.toString());
            }
        } finally {
            CloseableUtils.close(stream);
        }
        return true;
    }
}
