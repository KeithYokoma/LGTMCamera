package jp.yokomark.lgtm.app.compose.ui.helper;

import android.app.ActionBar;
import android.content.Context;
import android.view.ViewGroup;
import com.anprosit.android.dagger.annotation.ForActivity;

import javax.inject.Inject;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.compose.entity.ComposeData;
import jp.yokomark.lgtm.app.compose.widget.ComposeView;
import jp.yokomark.lgtm.misc.ui.helper.AbstractActivityHelper;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComposeViewHelper extends AbstractActivityHelper {
    public static final String TAG = ComposeViewHelper.class.getSimpleName();

    @Inject
    ComposeViewHelper(@ForActivity Context context) {
        super(context);
    }

    public void setUpActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    public void setUpComposeView(ComposeData data) {
        ViewGroup container = findViewById(R.id.container_compose_view);
        container.addView(new ComposeView(getContext(), data));
    }
}
