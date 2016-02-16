package com.ivanferdelja.coloredtabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class StoryFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    StoryAdapter storyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_stories, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.story_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        storyAdapter = new StoryAdapter(buildDummyStories());
        recyclerView.setAdapter(storyAdapter);

        return view;
    }

    private List<Story> buildDummyStories() {
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(Story.create(R.drawable.story2, getString(R.string.story2_title)));
        stories.add(Story.create(R.drawable.story1, getString(R.string.story1_title)));
        stories.add(Story.create(R.drawable.story3, getString(R.string.story3_title)));
        stories.add(Story.create(R.drawable.windmills, getString(R.string.story4_title)));
        stories.add(Story.create(R.drawable.agriculture, getString(R.string.story5_title)));
        stories.add(Story.create(R.drawable.reef, getString(R.string.story6_title)));
        stories.add(Story.create(R.drawable.landscape, getString(R.string.story7_title)));
        return stories;
    }
}