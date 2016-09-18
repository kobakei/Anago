package io.github.kobakei.anago.viewmodel;

import android.content.Context;

/**
 * Created by keisuke on 2016/09/18.
 */

public abstract class ListItemViewModel {

    private final Context context;

    public ListItemViewModel(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }
}
