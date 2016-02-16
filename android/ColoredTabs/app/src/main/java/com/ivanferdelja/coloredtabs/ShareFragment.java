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

public class ShareFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ShareAdapter shareAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_share, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.share_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

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
        items.add(ShareItem.create(R.drawable.face3, getString(R.string.share3_text), R.drawable.nature4, "45m"));
        items.add(ShareItem.create(R.drawable.face4, getString(R.string.share4_text), R.drawable.nature3, "1h"));
        return items;
    }
}