package io.github.kobakei.anago.util;

import android.databinding.BindingAdapter;
import android.databinding.adapters.AbsListViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by keisuke on 2016/09/18.
 */

public class BindingAdapterUtil {
    @BindingAdapter(value = {"imageUrl", "errorDrawable"}, requireAll = false)
    public static void loadImage(ImageView view, String url, Drawable error) {
        Glide.with(view.getContext()).load(url).error(error).into(view);
    }

    @BindingAdapter(value = {"onScrolled", "onScrollStateChanged"}, requireAll = false)
    public static void setOnScroll(RecyclerView recyclerView,
                                   final OnScroll onScroll,
                                   final OnScrollStateChanged onScrollStateChanged) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (onScrollStateChanged != null) {
                    onScrollStateChanged.onScrollStateChanged(recyclerView, newState);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (onScroll != null) {
                    onScroll.onScrolled(recyclerView, dx, dy);
                }
            }
        });
    }

    @BindingAdapter(value = {"onPageSelected"})
    public static void setOnPageSelected(ViewPager viewPager, final OnPageSelected onPageSelected) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (onPageSelected != null) {
                    onPageSelected.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public interface OnScroll {
        void onScrolled(RecyclerView recyclerView, int dx, int dy);
    }

    public interface OnScrollStateChanged {
        void onScrollStateChanged(RecyclerView recyclerView, int newState);
    }

    public interface OnPageSelected {
        void onPageSelected(int position);
    }
}
