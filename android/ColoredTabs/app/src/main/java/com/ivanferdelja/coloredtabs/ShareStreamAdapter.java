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
        streamView.getLayoutParams().height = stream.shareItems.size()
                * getContext().getResources().getDimensionPixelSize(R.dimen.share_item_height)
                + (stream.shareItems.size() - 1)
                * getContext().getResources().getDimensionPixelSize(R.dimen.share_stream_divider_height)
                + 2 * getContext().getResources().getDimensionPixelSize(R.dimen.fragment_item_inset);
        ((ListView) streamView).setAdapter(adapter);

        return streamView;
    }
}