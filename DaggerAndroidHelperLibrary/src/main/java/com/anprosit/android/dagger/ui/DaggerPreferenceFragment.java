package com.anprosit.android.dagger.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class DaggerPreferenceFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DaggerActivity) getActivity()).inject(this);
    }
}
