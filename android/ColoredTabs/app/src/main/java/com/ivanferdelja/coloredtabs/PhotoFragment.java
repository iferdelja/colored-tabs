package com.ivanferdelja.coloredtabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;

public  class PhotoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_photos, container, false);
        final GridView gridView = (GridView) view.findViewById(R.id.grid);
        gridView.setAdapter(new ImageAdapter(getContext()));
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
//
                Log.d("coloredtabs", "firstVisible " + firstVisibleItem + " visibleCount " + visibleItemCount
                        + " totalCount " + totalItemCount);

//                ((Application) getContext().getApplicationContext()).getBus().post(
//                        new ContentScrollStateChange(firstVisibleItem >= totalItemCount - visibleItemCount));

            }
        });
        return view;
    }
}