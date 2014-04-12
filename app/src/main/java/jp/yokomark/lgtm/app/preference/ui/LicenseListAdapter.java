package jp.yokomark.lgtm.app.preference.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.preference.entity.LicenseEntry;
import jp.yokomark.lgtm.app.preference.model.LicenseCollection;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class LicenseListAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    public static final String TAG = LicenseListAdapter.class.getSimpleName();
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final LicenseCollection mCollection;

    public LicenseListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mCollection = new LicenseCollection.Builder()
                .addAsApache(new LicenseEntry("Android-Device-Compatibility", "mixi, Inc.", "https://github.com/mixi-inc/Android-Device-Compatibility", LicenseEntry.Category.APACHE))
                .addAsApache(new LicenseEntry("Amalgam", "nohana, Inc.", "https://github.com/nohana/Amalgam", LicenseEntry.Category.APACHE))
                .addAsApache(new LicenseEntry("CompoundContainers", "KeithYokoma", "https://github.com/KeithYokoma/CompoundContainers", LicenseEntry.Category.APACHE))
                .addAsApache(new LicenseEntry("Dagger", "Square, Inc.", "https://github.com/square/dagger", LicenseEntry.Category.APACHE))
                .addAsApache(new LicenseEntry("DaggerAndroidHelperLibrary", "hnakagawa", "https://github.com/hnakagawa/dagger-android-helper-library", LicenseEntry.Category.APACHE))
                .addAsApache(new LicenseEntry("Laevatein", "nohana, Inc.", "https://github.com/nohana/Laevatein", LicenseEntry.Category.APACHE))
                .addAsApache(new LicenseEntry("Otto", "Square, Inc.", "https://github.com/square/otto", LicenseEntry.Category.APACHE))
                .addAsApache(new LicenseEntry("Picasso", "Square, Inc.", "https://github.com/square/picasso", LicenseEntry.Category.APACHE))
                .addAsApache(new LicenseEntry("StickyListHeaders", "Emil Sjölander", "https://github.com/emilsjolander/StickyListHeaders", LicenseEntry.Category.APACHE))
                .addAsMit(new LicenseEntry("Image View Zoom", "Alessandro Crugnola", "https://github.com/sephiroth74/ImageViewZoom", LicenseEntry.Category.MIT))
                .addAsMit(new LicenseEntry("Android-Bootstrap", "Bearded Hen", "https://github.com/Bearded-Hen/Android-Bootstrap", LicenseEntry.Category.MIT))
                .create();
    }

    @Override
    public long getHeaderId(int pos) {
        return mCollection.get(pos).getCategory().getId();
    }

    @Override
    public int getCount() {
        return mCollection.count();
    }

    @Override
    public Object getItem(int position) {
        return mCollection.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder holder;
        if (convertView == null) {
            holder = new ItemViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_license, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.label_library);
            convertView.setTag(holder);
        } else {
            holder = (ItemViewHolder) convertView.getTag();
        }

        LicenseEntry entry = (LicenseEntry) getItem(position);
        String label = mContext.getString(R.string.label_list_item_license, entry.getLibraryName(), entry.getOwnerName());
        holder.text.setText(label);
        return convertView;
    }

    @Override
    public View getHeaderView(int pos, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.list_sticky_header_license, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.label_license);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        LicenseEntry.Category category = ((LicenseEntry) getItem(pos)).getCategory();
        holder.text.setText(category.getLabelId());
        return convertView;
    }

    /* package */ static class ItemViewHolder {
        TextView text;
    }

    /* package */ static class HeaderViewHolder {
        TextView text;
    }
}
