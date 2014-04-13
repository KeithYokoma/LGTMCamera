package jp.yokomark.lgtm.app.compose.ui.helper;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.almeros.android.multitouch.gesturedetectors.MoveGestureDetector;
import com.anprosit.android.dagger.annotation.ForActivity;
import com.squareup.picasso.Picasso;

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
public class ComposeViewHelper extends AbstractActivityHelper implements MoveGestureDetector.OnMoveGestureListener, ScaleGestureDetector.OnScaleGestureListener {
    public static final String TAG = ComposeViewHelper.class.getSimpleName();
    private MoveGestureDetector mMoveDetector;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.0f;
    private float mFocusX;
    private float mFocusY;

    @Inject
    ComposeViewHelper(@ForActivity Context context) {
        super(context);
        mMoveDetector = new MoveGestureDetector(context, this);
        mScaleDetector = new ScaleGestureDetector(context, this);
    }

    public void setSurface(ComposeData data) {
        ViewGroup container = findViewById(R.id.container_compose_view);
        container.addView(new ComposeView(getContext(), data));
    }

    public void assignImage(ComposeData data, Point size) {
        ImageView imageView = findViewById(R.id.image);
        Picasso.with(getContext()).load(data.getImage()).resize(size.x, size.y).centerInside().noFade().into(imageView);
    }

    public void assignText(ComposeData data) {
        final TextView textView = findViewById(R.id.text_lgtm);
        textView.setText(data.getText());
        textView.setTextSize(data.getTextSize());
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mScaleDetector.onTouchEvent(event);
                mMoveDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    @Override
    public boolean onMove(MoveGestureDetector detector) {
        PointF p = detector.getFocusDelta();
        mFocusX += p.x;
        mFocusY += p.y;
        View view = findViewById(R.id.text_lgtm);
        view.setX(mFocusX);
        view.setY(mFocusY);
        return true;
    }

    @Override
    public boolean onMoveBegin(MoveGestureDetector detector) {
        return true;
    }

    @Override
    public void onMoveEnd(MoveGestureDetector detector) {
        // nothing to do
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        mScaleFactor *= detector.getScaleFactor();
        View view = findViewById(R.id.text_lgtm);
        view.setPivotX(detector.getFocusX());
        view.setPivotY(detector.getFocusY());
        view.setScaleX(mScaleFactor);
        view.setScaleY(mScaleFactor);
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        // nothing to do
    }
}
