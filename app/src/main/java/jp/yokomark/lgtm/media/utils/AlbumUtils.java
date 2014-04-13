package jp.yokomark.lgtm.media.utils;

import android.os.Environment;

import java.util.Locale;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public final class AlbumUtils {
    public static final String BUCKET_NAME = "LGTMCamera";
    public static final String IMAGE_STORE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + BUCKET_NAME;

    private AlbumUtils() {
        throw new AssertionError();
    }

    public static int getBucketIdHash() {
        return getBucketIdHash(IMAGE_STORE_DIR);
    }

    public static int getBucketIdHash(String imageFilePath) {
        return imageFilePath.toLowerCase(Locale.US).hashCode();
    }
}
