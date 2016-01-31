package com.ivanferdelja.coloredtabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryAdapter extends ArrayAdapter<Story> {

    public StoryAdapter(Context context, int resource) {
        super(context, resource);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View storyView = convertView;
        if (storyView == null) {
            storyView = LayoutInflater.from(getContext()).inflate(R.layout.story, null);
        }

        Story story = getItem(position);

        ImageView storyImage = (ImageView) storyView.findViewById(R.id.image);
        storyImage.setImageResource(story.imageResource);
        TextView headline = (TextView) storyView.findViewById(R.id.headline);
        headline.setText(story.headline);
        return storyView;
    }
}