package com.ivanferdelja.coloredtabs;

import java.util.ArrayList;
import java.util.List;

public class ShareStream {
    public List<ShareItem> shareItems = new ArrayList<>();

    public static ShareStream create() {
        ShareStream stream = new ShareStream();
        return stream;
    }
}
