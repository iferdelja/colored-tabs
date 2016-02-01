package com.ivanferdelja.coloredtabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareItemAdapter extends ArrayAdapter<ShareItem> {

    public ShareItemAdapter(Context context) {
        super(context, 0);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View shareItemView = convertView;
        if (shareItemView == null) {
            shareItemView = LayoutInflater.from(getContext()).inflate(R.layout.share_item, null);
        }

        ShareItem shareItem = getItem(position);

        ImageView authorImage = (ImageView) shareItemView.findViewById(R.id.author);
        authorImage.setImageResource(shareItem.authorImageResource);

        TextView title = (TextView) shareItemView.findViewById(R.id.title);
        title.setText(shareItem.title);

        ImageView imageView = (ImageView) shareItemView.findViewById(R.id.image);
        imageView.setImageResource(shareItem.imageResource);

        return shareItemView;
    }
}