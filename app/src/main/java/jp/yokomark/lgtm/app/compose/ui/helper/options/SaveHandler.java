package jp.yokomark.lgtm.app.compose.ui.helper.options;

import android.graphics.Bitmap;

import com.amalgam.app.ProgressDialogFragment;

import javax.inject.Inject;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.compose.model.ComposeStateHolder;
import jp.yokomark.lgtm.app.compose.ui.ComposeActivity;
import jp.yokomark.lgtm.app.compose.ui.helper.ComposeViewHelper;
import jp.yokomark.lgtm.misc.ui.helper.OptionsMenuHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class SaveHandler implements OptionsMenuHandler<ComposeActivity> {
    @Inject ComposeViewHelper mHelper;
    @Inject ComposeStateHolder mHolder;

    @Override
    public boolean handle(ComposeActivity activity) {
        activity.inject(this);

        ProgressDialogFragment dialog = ProgressDialogFragment.newInstance(
                null, activity.getString(R.string.label_progress_save), true);
        dialog.show(activity.getFragmentManager(), ProgressDialogFragment.TAG);
        Bitmap bitmap = mHelper.captureComposedImage();
        mHolder.save(bitmap);
        return true;
    }
}
