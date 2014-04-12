package com.anprosit.android.dagger.ui;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by Hirofumi Nakagawa on 13/07/21.
 */
public class DaggerFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DaggerActivity) getActivity()).inject(this);
    }
}
