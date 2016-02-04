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
            int imageResource = R.drawable.nature3;
            int mod = position % 10;
            switch (mod) {
                case 0:
                    imageResource = R.drawable.nature3;
                    break;
                case 1:
                case 4:
                    imageResource = R.drawable.nature4;
                    break;
                case 5:
                    imageResource = R.drawable.nature5;
                    break;
                case 3:
                case 6:
                    imageResource = R.drawable.nature6;
                    break;
                case 7:
                    imageResource = R.drawable.nature7;
                    break;
                case 8:
                    imageResource = R.drawable.nature8;
                    break;
                case 9:
                    imageResource = R.drawable.nature9;
                    break;
            }
            imageView.setImageResource(imageResource);
        } else {
            imageView = (ImageView) convertView;
        }

        return imageView;
    }
}