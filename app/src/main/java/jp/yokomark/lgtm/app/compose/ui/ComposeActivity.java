package jp.yokomark.lgtm.app.compose.ui;

import android.net.Uri;
import android.os.Bundle;

import com.amalgam.os.BundleUtils;
import com.anprosit.android.dagger.ActivityModule;
import com.anprosit.android.dagger.ui.DaggerActivity;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.compose.model.ComposeStateHolder;
import jp.yokomark.lgtm.app.compose.ui.helper.ComposeModule;
import jp.yokomark.lgtm.app.compose.ui.helper.ComposeViewHelper;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        mHolder.onCreate(getIntent().<Uri>getParcelableExtra(EXTRA_URI), savedInstanceState);
//        mHelper.assignImage(mHolder.getData(), mHolder.getImageViewDimension());
//        mHelper.assignText(mHolder.getData());
        mHelper.setSurface(mHolder.getData());
    }

    @Override
    protected void onSaveInstanceState(@Nonnull Bundle outState) {
        mHolder.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.asList(new ActivityModule(this), new ComposeModule());
    }
}
