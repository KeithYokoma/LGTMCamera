package jp.yokomark.lgtm.app.history.ui;

import android.os.Bundle;

import com.anprosit.android.dagger.ActivityModule;
import com.anprosit.android.dagger.ui.DaggerActivity;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import jp.yokomark.lgtm.app.history.event.HistoryInvalidatedEvent;
import jp.yokomark.lgtm.app.history.event.HistoryLoadedEvent;
import jp.yokomark.lgtm.app.history.model.ComposeHistoryCollection;
import jp.yokomark.lgtm.app.history.ui.helper.ComposeHistoryModule;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComposeHistoryActivity extends DaggerActivity {
    public static final String TAG = ComposeHistoryActivity.class.getSimpleName();
    @Inject ComposeHistoryCollection mCollection;
    @Inject Bus mBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBus.register(this);
        mCollection.load();
    }

    @Override
    protected void onDestroy() {
        mBus.unregister(this);
        mCollection.onDestroy();
        super.onDestroy();
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.asList(new ActivityModule(this), new ComposeHistoryModule());
    }

    @Subscribe
    public void onLoadHistory(HistoryLoadedEvent event) {

    }

    @Subscribe
    public void onInvalidateHistory(HistoryInvalidatedEvent event) {

    }
}
