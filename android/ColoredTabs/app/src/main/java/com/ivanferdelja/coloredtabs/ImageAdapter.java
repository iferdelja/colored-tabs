package com.ivanferdelja.coloredtabs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return 50;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            int imageResource = R.drawable.dog;
            if (position % 2 == 0) {
                imageResource = R.drawable.dog2;
            } else if (position % 3 == 0) {
                imageResource = R.drawable.dog3;
            }
            imageView.setImageResource(imageResource);
        } else {
            imageView = (ImageView) convertView;
        }

        return imageView;
    }
}