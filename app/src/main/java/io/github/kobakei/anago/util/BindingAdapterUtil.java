package io.github.kobakei.anago.util;

import android.databinding.BindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by keisuke on 2016/09/18.
 */

public class BindingAdapterUtil {
    @BindingAdapter({"imageUrl", "errorDrawable"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Glide.with(view.getContext()).load(url).error(error).into(view);
    }
}
