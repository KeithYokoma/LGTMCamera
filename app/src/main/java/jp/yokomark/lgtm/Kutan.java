package jp.yokomark.lgtm;

import com.anprosit.android.dagger.AndroidModule;
import com.anprosit.android.dagger.application.DaggerApplication;

import java.util.Arrays;
import java.util.List;

/**
 * @author yokomakukeishin
 * @version 1.0.0
 * @since 1.0.0
 */
public class Kutan extends DaggerApplication {
    public static final String TAG = Kutan.class.getSimpleName();

    @Override
    protected List<Object> getModules() {
        return Arrays.asList(new AndroidModule(this), new KutanModule());
    }
}
