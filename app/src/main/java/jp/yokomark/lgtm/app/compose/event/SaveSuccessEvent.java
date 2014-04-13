package jp.yokomark.lgtm.app.compose.event;

import android.net.Uri;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class SaveSuccessEvent {
    private final Uri mUri;

    public SaveSuccessEvent(Uri uri) {
        mUri = uri;

    }

    public Uri getUri() {
        return mUri;
    }
}
