package com.ivanferdelja.coloredtabs;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoryFragment extends Fragment implements StoryAdapter.OnSurfaceAvailableListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    StoryAdapter storyAdapter;

    MediaPlayer mediaPlayer;
    boolean isPrepared;
    Surface surface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_stories, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.story_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        storyAdapter = new StoryAdapter(buildDummyStories());
        storyAdapter.setListener(this);
        recyclerView.setAdapter(storyAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Uri uri;
        try {
            uri = Uri.parse("android.resource://"
                    + getContext().getPackageName() + "/raw/"
                    + getResources().getResourceEntryName(R.raw.samplevideo_1280x720_2mb));

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getContext(), uri);
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Log.d("coloredTabs", "video is prepared");
                    mediaPlayer.setVolume(0.0f, 0.0f);
                    isPrepared = true;
                    mediaPlayer.setSurface(surface);
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                    Log.d("coloredTabs", "MediaPlayer error " + what + " " + extra);
                    return false;
                }
            });
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private List<Story> buildDummyStories() {
        ArrayList<Story> stories = new ArrayList<>();

        stories.add(Story.create(R.drawable.story2, getString(R.string.story2_title)));
        stories.add(Story.create(R.drawable.story3, getString(R.string.video_story_title), true));
        stories.add(Story.create(R.drawable.story1, getString(R.string.story1_title)));
        stories.add(Story.create(R.drawable.story3, getString(R.string.story3_title)));
        stories.add(Story.create(R.drawable.windmills, getString(R.string.story4_title)));
        stories.add(Story.create(R.drawable.agriculture, getString(R.string.story5_title)));
        stories.add(Story.create(R.drawable.reef, getString(R.string.story6_title)));
        stories.add(Story.create(R.drawable.landscape, getString(R.string.story7_title)));
        return stories;
    }

    @Override
    public void onSurfaceAvailable(Surface surface) {
        this.surface = surface;
        mediaPlayer.setSurface(this.surface);
    }
}