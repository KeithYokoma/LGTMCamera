package jp.yokomark.lgtm.app.compose.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.almeros.android.multitouch.gesturedetectors.MoveGestureDetector;
import com.almeros.android.multitouch.gesturedetectors.RotateGestureDetector;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import javax.annotation.Nonnull;

import jp.yokomark.lgtm.app.compose.entity.ComposeData;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComposeView extends SurfaceView implements SurfaceHolder.Callback, Runnable, MoveGestureDetector.OnMoveGestureListener, ScaleGestureDetector.OnScaleGestureListener, RotateGestureDetector.OnRotateGestureListener {
    public static final String TAG = ComposeView.class.getSimpleName();
    private final int mTextSize;
    private ComposeData mData;
    private Thread mLooper;
    private SurfaceHolder mHolder;
    private RotateGestureDetector mRotateDetector;
    private MoveGestureDetector mMoveDetector;
    private ScaleGestureDetector mScaleDetector;
    private int mWidth;
    private int mHeight;
    private float mTextBaseX;
    private float mTextBaseY;
    private float mTextX;
    private float mTextY;
    private float mScaleFactor;
    private float mDegree;
    private Paint mTextPaint;
    private Bitmap mBackground;

    public ComposeView(Context context, ComposeData data) {
        super(context);
        mData = data;
        mMoveDetector = new MoveGestureDetector(context, this);
        mScaleDetector = new ScaleGestureDetector(context, this);
        mRotateDetector = new RotateGestureDetector(context, this);
        getHolder().addCallback(this);
        mTextSize = mData.getTextSize();

        mScaleFactor = 1.f;

        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mLooper = new Thread(this);
        mHolder = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(mLooper != null ) {
            mWidth = width;
            mHeight = height;
            Paint.FontMetrics metrics = mTextPaint.getFontMetrics();
            // 文字列の幅を取得
            float textWidth = mTextPaint.measureText(mData.getText());
            // 中心にしたいX座標から文字列の幅の半分を引く
            mTextBaseX = (mWidth / 2) - textWidth / 2;
            // 中心にしたいY座標からAscentとDescentの半分を引く
            mTextBaseY = (mHeight / 2) - (metrics.ascent + metrics.descent) / 2;
            mLooper.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mLooper = null;
    }

    @Override
    public void run() {
        doDraw();
    }

    @Override
    public boolean onTouchEvent(@Nonnull MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        mRotateDetector.onTouchEvent(event);
        mMoveDetector.onTouchEvent(event);

        mLooper = new Thread(this);
        mLooper.start();
        return true;
    }

    /* package */ void doDraw() {
        try {
            if (mBackground == null) {
                mBackground = Picasso.with(getContext()).load(mData.getImage()).resize(mWidth, mHeight).centerInside().get();
            }
            Canvas canvas = mHolder.lockCanvas();
            drawBackground(canvas);
            drawText(canvas);
            mHolder.unlockCanvasAndPost(canvas);
        } catch (IOException e) {
            Log.e(TAG, "io error: ", e);
        }
    }

    /* package */ void drawBackground(Canvas canvas) {
        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, mWidth, mHeight, backgroundPaint);
        canvas.drawBitmap(mBackground, new Matrix(), null);
    }

    /* package */ void drawText(Canvas canvas) {
        canvas.save();
        float textSize = mTextSize * mScaleFactor;
        float textX = mTextBaseX + mTextX;
        float textY = mTextBaseY + mTextY;

        canvas.rotate(mDegree, textX, textY);

        mTextPaint.setTextSize(textSize < 255.f ? textSize : 255.f);
        canvas.drawText(mData.getText(), textX, textY, mTextPaint);

        float strokeSize = 0.5f * mScaleFactor;
        Paint strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(strokeSize < 3.f ? strokeSize : 3.f);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setTextSize(textSize < 255.f ? textSize : 255.f);
        strokePaint.setTypeface(Typeface.DEFAULT_BOLD);
        strokePaint.setAntiAlias(true);
        canvas.drawText(mData.getText(), textX, textY, strokePaint);
        canvas.restore();
    }

    @Override
    public boolean onMove(MoveGestureDetector detector) {
        PointF p = detector.getFocusDelta();
        mTextX += p.x;
        mTextY += p.y;
        return true;
    }

    @Override
    public boolean onMoveBegin(MoveGestureDetector detector) {
        return true;
    }

    @Override
    public void onMoveEnd(MoveGestureDetector detector) {}

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        mScaleFactor *= detector.getScaleFactor();
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {}

    @Override
    public boolean onRotate(RotateGestureDetector detector) {
        mDegree -= detector.getRotationDegreesDelta();
        return true;
    }

    @Override
    public boolean onRotateBegin(RotateGestureDetector detector) {
        return true;
    }

    @Override
    public void onRotateEnd(RotateGestureDetector detector) {}
}
