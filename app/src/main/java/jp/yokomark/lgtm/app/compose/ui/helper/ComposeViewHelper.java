package jp.yokomark.lgtm.app.compose.ui.helper;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.ViewGroup;

import com.amalgam.app.ProgressDialogFragment;
import com.amalgam.os.HandlerUtils;
import com.anprosit.android.dagger.annotation.ForActivity;

import javax.inject.Inject;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.compose.entity.ComposeData;
import jp.yokomark.lgtm.app.compose.widget.ComposeView;
import jp.yokomark.lgtm.media.utils.ImageUtils;
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
        ComposeView view = new ComposeView(getContext(), data);
        view.setId(R.id.view_compose);
        container.addView(view);
    }

    public Bitmap captureComposedImage() {
        ViewGroup container = findViewById(R.id.container_compose_view);
        ComposeView composeView = (ComposeView) container.findViewById(R.id.view_compose);
        return composeView.capture();
    }

    public void dismissProgress() {
        HandlerUtils.postOnMain(new Runnable() {
            @Override
            public void run() {
                FragmentManager manager = getActivity().getFragmentManager();
                DialogFragment dialog = (DialogFragment) manager.findFragmentByTag(ProgressDialogFragment.TAG);
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
    }

    public void dispatchShare(Uri uri) {
        Log.v(TAG, ImageUtils.getPath(getContext().getContentResolver(), uri));
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setDataAndType(uri, "image/jpeg");
        intent.putExtra(Intent.EXTRA_TEXT, getContext().getString(R.string.default_lgtm_text));
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        getContext().startActivity(intent);
        getActivity().finish();
    }
}
