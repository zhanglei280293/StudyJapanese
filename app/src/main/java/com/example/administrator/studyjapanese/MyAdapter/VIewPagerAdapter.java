package com.example.administrator.studyjapanese.MyAdapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/16 0016.
 */
public class VIewPagerAdapter extends PagerAdapter {

    private ArrayList<ImageView> imags;

    public VIewPagerAdapter(ArrayList<ImageView> imags) {
        this.imags = imags;
    }

    @Override
    public int getCount() {
        int i = 0;
        if (imags != null) {
           i = imags.size();
        }
        return i;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    //当view消失时调用次方法
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imags.get(position));
    }
    //viewpager只会缓存三张图片，当调用缓存图片时会调用此方法
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imags.get(position));
        return imags.get(position);
    }
}
