package jp.yokomark.lgtm.app.compose.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.amalgam.os.BundleUtils;
import com.anprosit.android.dagger.ActivityModule;
import com.anprosit.android.dagger.ui.DaggerActivity;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.compose.event.SaveSuccessEvent;
import jp.yokomark.lgtm.app.compose.model.ComposeStateHolder;
import jp.yokomark.lgtm.app.compose.ui.helper.ComposeModule;
import jp.yokomark.lgtm.app.compose.ui.helper.ComposeViewHelper;
import jp.yokomark.lgtm.app.compose.ui.helper.options.ComposeOptionsMenu;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComposeActivity extends DaggerActivity {
    public static final String TAG = ComposeActivity.class.getSimpleName();
    public static final String EXTRA_URI = BundleUtils.buildKey(ComposeActivity.class, "EXTRA_URI");
    @Inject ComposeStateHolder mHolder;
    @Inject ComposeViewHelper mHelper;
    @Inject Bus mBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        mBus.register(this);
        mHolder.onCreate(getIntent().<Uri>getParcelableExtra(EXTRA_URI), savedInstanceState);
        mHelper.setUpActionBar();
        mHelper.setUpComposeView(mHolder.getData());
    }

    @Override
    protected void onSaveInstanceState(@Nonnull Bundle outState) {
        mHolder.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        mBus.unregister(this);
        mHolder.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_activity_compose, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ComposeOptionsMenu menu = ComposeOptionsMenu.valueOf(item);
        return menu.getHandler().handle(this) || super.onOptionsItemSelected(item);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.asList(new ActivityModule(this), new ComposeModule());
    }

    @Subscribe
    public void onSaveSuccess(SaveSuccessEvent event) {
        mHelper.dismissProgress();
        mHelper.dispatchShare(event.getUri());
    }
}
