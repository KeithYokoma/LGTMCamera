package jp.yokomark.lgtm.app.license.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.yokomark.lgtm.app.license.entity.LicenseEntry;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class LicenseCollection {
    public static final String TAG = LicenseCollection.class.getSimpleName();
    private List<LicenseEntry> mEntries;

    /* package */ LicenseCollection(List<LicenseEntry> entries) {
        mEntries = new ArrayList<LicenseEntry>();
        mEntries.addAll(entries);
        Collections.sort(mEntries);
    }

    public List<LicenseEntry> get() {
        return Collections.unmodifiableList(mEntries);
    }

    public LicenseEntry get(int position) {
        return mEntries.get(position);
    }

    public int count() {
        return mEntries.size();
    }

    public static class Builder {
        private final List<LicenseEntry> mMutableEntries;

        public Builder() {
            mMutableEntries = new ArrayList<LicenseEntry>();
        }

        public Builder add(LicenseEntry entry) {
            mMutableEntries.add(entry);
            return this;
        }

        public LicenseCollection create() {
            return new LicenseCollection(mMutableEntries);
        }
    }
}
