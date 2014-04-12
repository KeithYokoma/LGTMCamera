package jp.yokomark.lgtm.app.preference.ui.helper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.anprosit.android.dagger.annotation.ForActivity;

import javax.inject.Inject;

import jp.yokomark.lgtm.R;
import jp.yokomark.lgtm.app.preference.entity.LicenseEntry;
import jp.yokomark.lgtm.app.preference.ui.LicenseListAdapter;
import jp.yokomark.lgtm.misc.ui.helper.AbstractActivityHelper;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class LicenseListHelper extends AbstractActivityHelper implements AdapterView.OnItemClickListener {
    public static final String TAG = LicenseListHelper.class.getSimpleName();

    @Inject
    LicenseListHelper(@ForActivity Context context) {
        super(context);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LicenseEntry entry = (LicenseEntry) parent.getItemAtPosition(position);
        if (entry == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, entry.getOpenUri());
        getContext().startActivity(intent);
    }

    public void setUpLicenseListView() {
        StickyListHeadersListView list = findViewById(R.id.list_license);
        list.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.list_header_license, null, false));
        list.setAdapter(new LicenseListAdapter(getContext()));
        list.setOnItemClickListener(this);
    }
}
