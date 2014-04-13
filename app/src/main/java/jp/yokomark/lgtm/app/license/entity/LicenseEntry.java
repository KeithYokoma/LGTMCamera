package jp.yokomark.lgtm.app.license.entity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import jp.yokomark.lgtm.R;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class LicenseEntry implements Parcelable, Comparable<LicenseEntry> {
    public static final Creator<LicenseEntry> CREATOR = new Creator<LicenseEntry>() {
        @Override
        public LicenseEntry createFromParcel(Parcel source) {
            return new LicenseEntry(source);
        }

        @Override
        public LicenseEntry[] newArray(int size) {
            return new LicenseEntry[size];
        }
    };
    private final String mLibraryName;
    private final String mOwnerName;
    private final Uri mOpenUri;
    private final Category mCategory;

    public LicenseEntry(String libraryName, String ownerName, String openUri, Category category) {
        this(libraryName, ownerName, Uri.parse(openUri), category);
    }

    /* package */ LicenseEntry(Parcel source) {
        mLibraryName = source.readString();
        mOwnerName = source.readString();
        mOpenUri = source.readParcelable(Uri.class.getClassLoader());
        mCategory = (Category) source.readSerializable();
    }

    /* package */ LicenseEntry(String libraryName, String ownerName, Uri openUri, Category category) {
        mLibraryName = libraryName;
        mOwnerName = ownerName;
        mOpenUri = openUri;
        mCategory = category;
    }

    @Override
    public int compareTo(LicenseEntry another) {
        return mCategory.compareTo(another.getCategory());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mLibraryName);
        dest.writeString(mOwnerName);
        dest.writeParcelable(mOpenUri, flags);
        dest.writeSerializable(mCategory);
    }

    public String getLibraryName() {
        return mLibraryName;
    }

    public String getOwnerName() {
        return mOwnerName;
    }

    public Uri getOpenUri() {
        return mOpenUri;
    }

    public Category getCategory() {
        return mCategory;
    }

    public static enum Category {
        BSD(0L, R.string.label_list_sticky_header_license_bsd_c2),
        MIT(1L,R.string.label_list_sticky_header_license_mit),
        APACHE(2L, R.string.label_list_sticky_header_license_apache);

        private final long mId;
        private final int mLabelId;

        private Category(long id, int labelId) {
            mId = id;
            mLabelId = labelId;
        }

        public long getId() {
            return mId;
        }

        public int getLabelId() {
            return mLabelId;
        }
    }
}
