package jp.yokomark.lgtm.app.history.model;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import com.anprosit.android.dagger.annotation.ForActivity;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import jp.yokomark.lgtm.app.history.event.HistoryInvalidatedEvent;
import jp.yokomark.lgtm.app.history.event.HistoryLoadedEvent;
import jp.yokomark.lgtm.misc.event.EventBusUtils;
import jp.yokomark.lgtm.misc.model.AbstractModel;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComposeHistoryCollection extends AbstractModel implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final String TAG = ComposeHistoryCollection.class.getSimpleName();
    private static final int LOADER_ID = 1;
    private static final String BUCKET_ID = "1111";
    @Inject Bus mBus;

    @Inject
    public ComposeHistoryCollection(@ForActivity Context context) {
        super(context);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media.BUCKET_ID + " = ?", new String[] {BUCKET_ID}, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        EventBusUtils.postOnMainThread(mBus, new HistoryLoadedEvent(data));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        EventBusUtils.postOnMainThread(mBus, new HistoryInvalidatedEvent());
    }

    public void load() {
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    public void onDestroy() {
        getLoaderManager().destroyLoader(LOADER_ID);
    }
}
