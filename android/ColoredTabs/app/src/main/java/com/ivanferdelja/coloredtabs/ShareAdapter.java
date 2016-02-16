package com.ivanferdelja.coloredtabs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ShareAdapter extends RecyclerView.Adapter<ShareAdapter.ViewHolder> {

    List<ShareItem> shareItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ViewHolder(View storyView) {
            super(storyView);
            this.view = storyView;
        }
    }

    public ShareAdapter(List<ShareItem> shareItems) {
        this.shareItems = shareItems;
    }

    @Override
    public int getItemCount() {
        return shareItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShareItem shareItem = shareItems.get(position);

        ImageView authorImage = (ImageView) holder.view.findViewById(R.id.author);
        authorImage.setImageResource(shareItem.authorImageResource);

        TextView title = (TextView) holder.view.findViewById(R.id.title);
        title.setText(shareItem.title);

        ImageView imageView = (ImageView) holder.view.findViewById(R.id.image);
        imageView.setImageResource(shareItem.imageResource);

        TextView timestamp = (TextView) holder.view.findViewById(R.id.timestamp);
        timestamp.setText(shareItem.timestamp);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.share_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
}