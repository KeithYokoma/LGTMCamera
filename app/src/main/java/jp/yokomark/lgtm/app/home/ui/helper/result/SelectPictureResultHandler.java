package jp.yokomark.lgtm.app.home.ui.helper.result;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.laevatein.Laevatein;

import java.util.List;

import jp.yokomark.lgtm.app.compose.ui.ComposeActivity;
import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.misc.ui.helper.RequestResultHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class SelectPictureResultHandler implements RequestResultHandler<MainActivity> {
    public static final String TAG = SelectPictureResultHandler.class.getSimpleName();

    @Override
    public void handle(MainActivity activity, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        List<Uri> uris = Laevatein.obtainResult(data);
        Intent intent = new Intent(activity, ComposeActivity.class);
        intent.putExtra(ComposeActivity.EXTRA_URI, uris.get(0));
        activity.startActivity(intent);
    }
}
