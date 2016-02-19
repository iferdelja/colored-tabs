package com.ivanferdelja.coloredtabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class PhotoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_photos, container, false);
        final GridView gridView = (GridView) view.findViewById(R.id.grid);
        gridView.setAdapter(new ImageAdapter(getContext()));
        return view;
    }
}