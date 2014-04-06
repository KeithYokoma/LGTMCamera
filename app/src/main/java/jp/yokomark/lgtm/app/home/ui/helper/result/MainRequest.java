package jp.yokomark.lgtm.app.home.ui.helper.result;

import jp.yokomark.lgtm.app.home.ui.MainActivity;
import jp.yokomark.lgtm.misc.ui.helper.RequestResultHandler;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public enum MainRequest {
    TAKE(0, new TakePictureResultHandler()),
    SELECT(1, new SelectPictureResultHandler()),
    UNKNOWN(2, new UnknownResultHandler());

    private final int mCode;
    private final RequestResultHandler<MainActivity> mHandler;

    private MainRequest(int code, RequestResultHandler<MainActivity> handler) {
        mCode = code;
        mHandler = handler;
    }

    public static MainRequest valueOf(int requestCode) {
        for (MainRequest request : values()) {
            if (request.mCode == requestCode) {
                return request;
            }
        }
        return UNKNOWN;
    }

    public int getCode() {
        return mCode;
    }

    public RequestResultHandler<MainActivity> getHandler() {
        return mHandler;
    }
}