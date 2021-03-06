package com.ivanferdelja.coloredtabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.*;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ShareFragment extends Fragment {

    RecyclerView recyclerView;
    android.support.v7.widget.LinearLayoutManager layoutManager;
    ShareAdapter shareAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_share, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.share_recycler_view);
        recyclerView.setHasFixedSize(false);

        // Problem with LinearLayoutManager - does not wrap its content
        // https://code.google.com/p/android/issues/detail?id=74772
        //layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //layoutManager.setChildSize(getResources().getDimensionPixelSize(R.dimen.share_item_height));
        recyclerView.setLayoutManager(layoutManager);
       // recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        shareAdapter = new ShareAdapter(buildDummyShareItems());
        recyclerView.setAdapter(shareAdapter);

        return view;
    }

    private List<ShareItem> buildDummyShareItems() {
        List<ShareItem> items = new ArrayList<>();
        items.add(ShareItem.create(R.drawable.face1, getString(R.string.share1_text), R.drawable.nature9, "10m"));
        items.add(ShareItem.create(R.drawable.face2, getString(R.string.share2_text), R.drawable.nature8, "10m"));
        items.add(ShareItem.create(R.drawable.face3, getString(R.string.share3_text), R.drawable.nature5, "10m"));
        items.add(ShareItem.create(R.drawable.face4, getString(R.string.share4_text), R.drawable.nature6, "10m"));
        items.add(ShareItem.create(R.drawable.face2, getString(R.string.share5_text), R.drawable.nature7, "40m"));
        items.add(ShareItem.create(R.drawable.face3, getString(R.string.share3_text), R.drawable.nature5, "45m"));
        items.add(ShareItem.create(R.drawable.face4, getString(R.string.share4_text), R.drawable.nature6, "1h"));
        items.add(ShareItem.create(R.drawable.face1, getString(R.string.share1_text), R.drawable.nature9, "10m"));
        items.add(ShareItem.create(R.drawable.face2, getString(R.string.share2_text), R.drawable.nature8, "10m"));
        items.add(ShareItem.create(R.drawable.face3, getString(R.string.share3_text), R.drawable.nature5, "10m"));
        items.add(ShareItem.create(R.drawable.face4, getString(R.string.share4_text), R.drawable.nature6, "10m"));
        items.add(ShareItem.create(R.drawable.face2, getString(R.string.share5_text), R.drawable.nature7, "40m"));
        items.add(ShareItem.create(R.drawable.face3, getString(R.string.share3_text), R.drawable.nature5, "45m"));
        items.add(ShareItem.create(R.drawable.face4, getString(R.string.share4_text), R.drawable.nature6, "1h"));
        return items;
    }
}