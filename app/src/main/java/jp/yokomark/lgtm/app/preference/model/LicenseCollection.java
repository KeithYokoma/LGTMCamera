package jp.yokomark.lgtm.app.preference.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.yokomark.lgtm.app.preference.entity.LicenseEntry;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class LicenseCollection {
    public static final String TAG = LicenseCollection.class.getSimpleName();
    private List<LicenseEntry> mEntries;

    /* package */ LicenseCollection(List<LicenseEntry> apache, List<LicenseEntry> mit) {
        mEntries = new ArrayList<LicenseEntry>();
        mEntries.addAll(mit);
        mEntries.addAll(apache);
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
        private final List<LicenseEntry> mApaches;
        private final List<LicenseEntry> mMits;

        public Builder() {
            mApaches = new ArrayList<LicenseEntry>();
            mMits = new ArrayList<LicenseEntry>();
        }

        public Builder addAsApache(LicenseEntry entry) {
            if (entry.getCategory() != LicenseEntry.Category.APACHE) {
                throw new IllegalArgumentException("invalid category");
            }
            mApaches.add(entry);
            return this;
        }

        public Builder addAsMit(LicenseEntry entry) {
            if (entry.getCategory() != LicenseEntry.Category.MIT) {
                throw new IllegalArgumentException("invalid category");
            }
            mMits.add(entry);
            return this;
        }

        public LicenseCollection create() {
            return new LicenseCollection(mApaches, mMits);
        }
    }
}
