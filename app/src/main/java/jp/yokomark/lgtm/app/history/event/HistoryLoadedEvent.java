package jp.yokomark.lgtm.app.history.event;

import android.database.Cursor;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class HistoryLoadedEvent {
    private final Cursor mCursor;

    public HistoryLoadedEvent(Cursor cursor) {
        mCursor = cursor;
    }

    public Cursor getCursor() {
        return mCursor;
    }
}
