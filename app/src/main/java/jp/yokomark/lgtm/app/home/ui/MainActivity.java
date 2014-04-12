package jp.yokomark.lgtm.app.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.anprosit.android.dagger.ActivityModule;
import com.anprosit.android.dagger.ui.DaggerActivity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import jp.mixi.compatibility.android.provider.MediaStoreCompat;
import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.home.model.TakePicState;
import jp.yokomark.lgtm.app.home.ui.helper.MainActivityHelper;
import jp.yokomark.lgtm.app.home.ui.helper.MainActivityModule;
import jp.yokomark.lgtm.app.home.ui.helper.button.MainViewClickEvent;
import jp.yokomark.lgtm.app.home.ui.helper.options.MainOptionsMenu;
import jp.yokomark.lgtm.app.home.ui.helper.result.MainRequest;

/**
 * @author KeithYokoma
 * @since 1.0.0
 * @version 1.0.0
 */
public class MainActivity extends DaggerActivity {
    @Inject MainActivityHelper mHelper;
    @Inject MediaStoreCompat mCompat;
    @Inject TakePicState mPicState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.asList(new ActivityModule(this), new MainActivityModule());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPicState.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mPicState.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MainRequest request = MainRequest.valueOf(requestCode);
        request.getHandler().handle(this, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MainOptionsMenu menu = MainOptionsMenu.valueOf(item);
        return menu.getHandler().handle(this) || super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View view) {
        MainViewClickEvent event = MainViewClickEvent.valueOf(view);
        event.getHandler().handle(this);
    }
}
