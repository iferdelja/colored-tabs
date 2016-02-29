package com.ivanferdelja.coloredtabs;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    List<Story> stories;
    OnSurfaceAvailableListener listener;

    public interface OnSurfaceAvailableListener {
        void onSurfaceAvailable(Surface surface);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ImageView storyImageView;
        public TextureView storyVideo;
        public TextView headline;
        public String id;

        public ViewHolder(View storyView) {
            super(storyView);
            this.view = storyView;
            storyImageView = (ImageView) storyView.findViewById(R.id.story_image);
            storyVideo = (TextureView) storyView.findViewById(R.id.story_video);
            headline = (TextView) storyView.findViewById(R.id.headline);
            this.id = UUID.randomUUID().toString().substring(0,3);
        }
    }

    public void setListener(OnSurfaceAvailableListener listener) {
        this.listener = listener;
    }

    public StoryAdapter(List<Story> stories) {
        this.stories = stories;
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }
//
//    @Override
//    public void onViewAttachedToWindow(ViewHolder holder) {
//        super.onViewAttachedToWindow(holder);
//        Log.d("coloredtabs", "onViewAttachedToWindow " + holder.id);
//
//    }
//
//    @Override
//    public void onViewDetachedFromWindow(ViewHolder holder) {
//        super.onViewDetachedFromWindow(holder);
//        Log.d("coloredtabs", "onViewDetachedFromWindow " + holder.id);
//
//    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Story story = stories.get(position);

        Log.d("coloredtabs", "onBind " + holder.id);
        View.OnClickListener uselessClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        };

        if (story.isVideo) {
            holder.storyImageView.setVisibility(View.GONE);
            holder.storyVideo.setVisibility(View.VISIBLE);
            holder.storyVideo.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
                @Override
                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                    Log.d("coloredTabs", "surfaceCreated " + holder.id);
                    if (listener != null) {
                        listener.onSurfaceAvailable(new Surface(surfaceTexture));
                    }
                }

                @Override
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

                }

                @Override
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    Log.d("coloredTabs", "surfaceDestroyed " + holder.id);
                    if (listener != null) {
                        listener.onSurfaceAvailable(null);
                    }
                    return false;
                }

                @Override
                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

                }
            });
//            holder.storyVideo.getHolder().addCallback(new SurfaceHolder.Callback() {
//                @Override
//                public void surfaceCreated(SurfaceHolder surfaceHolder) {
//                    Log.d("coloredTabs", "surfaceCreated " + holder.id);
//                    if (listener != null) {
//                        listener.onSurfaceAvailable(surfaceHolder.getSurface());
//                    }
//                }
//
//                @Override
//                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//                    Log.d("coloredTabs", "surfaceDestroyed " + holder.id);
//                    if (listener != null) {
//                        listener.onSurfaceAvailable(surfaceHolder.getSurface());
//                    }
//                }
//            });
        } else {
            holder.storyImageView.setVisibility(View.VISIBLE);
            holder.storyVideo.setVisibility(View.GONE);
            holder.storyImageView.setOnClickListener(uselessClickListener);
            Context context = holder.view.getContext();
            ((Application) context.getApplicationContext()).getBitmapManager().loadBitmap(
                    context, story.imageResource, holder.storyImageView);
        }

        holder.headline.setText(story.headline);
        TextView saveAction = (TextView) holder.view.findViewById(R.id.save);
        saveAction.setOnClickListener(uselessClickListener);
        TextView shareAction = (TextView) holder.view.findViewById(R.id.share);
        shareAction.setOnClickListener(uselessClickListener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View storyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.story, parent, false);
        ViewHolder viewHolder = new ViewHolder(storyView);
        Log.d("coloredtabs", "onCreated " + viewHolder.id);
        return viewHolder;
    }


}