package jp.yokomark.lgtm.app.history.ui.helper;

import android.app.ActionBar;
import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.anprosit.android.dagger.annotation.ForActivity;

import javax.inject.Inject;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.history.ui.ComposeHistoryAdapter;
import jp.yokomark.lgtm.misc.ui.helper.AbstractActivityHelper;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComposeHistoryViewHelper extends AbstractActivityHelper {
    public static final String TAG = ComposeHistoryViewHelper.class.getSimpleName();

    @Inject
    public ComposeHistoryViewHelper(@ForActivity Context context) {
        super(context);
    }

    public void setUpActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    public void setUpListView() {
        ListView listView = findViewById(R.id.list_history);
        ComposeHistoryAdapter adapter = new ComposeHistoryAdapter(getContext(), null);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
    }

    public void swapCursor(Cursor cursor) {
        ListView listView = findViewById(R.id.list_history);
        CursorAdapter adapter = (CursorAdapter) listView.getAdapter();
        adapter.swapCursor(cursor);
    }
}
