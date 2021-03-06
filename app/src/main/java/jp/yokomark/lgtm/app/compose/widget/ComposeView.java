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
    private static final float MAX_TEXT_SIZE = 255.f;
    private static final float MAX_STROKE_WIDTH = 5.f;
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
    private Paint mTextStrokePaint;
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
        mTextPaint.setColor(mData.getTextColor());
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextStrokePaint = new Paint();
        mTextStrokePaint.setStyle(Paint.Style.STROKE);
        mTextStrokePaint.setColor(Color.BLACK);
        mTextStrokePaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextStrokePaint.setAntiAlias(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mLooper = new Thread(this);
        mHolder = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(mLooper != null && !mLooper.isAlive()) {
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
        float textX = mTextBaseX + mTextX;
        float textY = mTextBaseY + mTextY;
        float textSize = Math.min(mTextSize * mScaleFactor, MAX_TEXT_SIZE);
        float strokeSize = Math.min(0.5f * mScaleFactor, MAX_STROKE_WIDTH);

        canvas.rotate(mDegree, textX, textY);

        mTextPaint.setTextSize(textSize);
        mTextStrokePaint.setTextSize(textSize);
        mTextStrokePaint.setStrokeWidth(strokeSize);

        canvas.drawText(mData.getText(), textX, textY, mTextPaint);
        canvas.drawText(mData.getText(), textX, textY, mTextStrokePaint);
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

    public Bitmap capture() {
        Bitmap bitmap = Bitmap.createBitmap(mBackground.getWidth(), mBackground.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        drawBackground(canvas);
        drawText(canvas);
        return bitmap;
    }
}
