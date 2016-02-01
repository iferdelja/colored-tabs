package com.ivanferdelja.coloredtabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShareStreamAdapter extends ArrayAdapter<ShareStream> {

    public ShareStreamAdapter(Context context) {
        super(context, 0);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View streamView = convertView;
        if (streamView == null) {
            streamView = LayoutInflater.from(getContext()).inflate(R.layout.share_stream, parent, false);
        }

        ShareStream stream = getItem(position);
        ShareItemAdapter adapter = new ShareItemAdapter(getContext());
        adapter.addAll(stream.shareItems);
        //streamView.getLayoutParams().height = 3000;
        ((ListView) streamView).setAdapter(adapter);

        return streamView;
    }
}