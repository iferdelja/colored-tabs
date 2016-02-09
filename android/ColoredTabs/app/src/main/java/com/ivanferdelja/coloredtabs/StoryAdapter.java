package com.ivanferdelja.coloredtabs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    List<Story> stories;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ViewHolder(View storyView) {
            super(storyView);
            this.view = storyView;
        }
    }

    public StoryAdapter(List<Story> stories) {
        this.stories = stories;
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Story story = stories.get(position);
        ImageView storyImage = (ImageView) holder.view.findViewById(R.id.image);
        storyImage.setImageResource(story.imageResource);
        TextView headline = (TextView) holder.view.findViewById(R.id.headline);
        headline.setText(story.headline);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View storyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.story, null);
        ViewHolder viewHolder = new ViewHolder(storyView);
        return viewHolder;
    }
}