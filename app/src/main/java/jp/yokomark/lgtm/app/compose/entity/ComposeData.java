package jp.yokomark.lgtm.app.compose.entity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Nullable;

import jp.yokomark.lgtm.R;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComposeData implements Parcelable {
    public static final Creator<ComposeData> CREATOR = new Creator<ComposeData>() {
        @Override
        @Nullable
        public ComposeData createFromParcel(Parcel source) {
            return new ComposeData(source);
        }

        @Override
        public ComposeData[] newArray(int size) {
            return new ComposeData[size];
        }
    };
    private Uri mImage;
    private String mText;
    private int mTextSize;
    private int mTextColor;

    /* package */ ComposeData(Parcel source) {
        mImage = source.readParcelable(Uri.class.getClassLoader());
        mText = source.readString();
        mTextSize = source.readInt();
        mTextColor = source.readInt();
    }

    public ComposeData(Uri image, String text, int textSize, int textColor) {
        mImage = image;
        mText = text;
        mTextSize = textSize;
        mTextColor = textColor;
    }

    public static ComposeData buildDefault(Context context, Uri uri) {
        return new ComposeData(uri, context.getString(R.string.default_lgtm_text), 40, Color.BLACK);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mImage, flags);
        dest.writeString(mText);
        dest.writeInt(mTextSize);
        dest.writeInt(mTextColor);
    }

    public Uri getImage() {
        return mImage;
    }

    public String getText() {
        return mText;
    }

    public int getTextSize() {
        return mTextSize;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setText(String text) {
        mText = text;
    }

    public void setTextSize(int textSize) {
        mTextSize = textSize;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }
}
