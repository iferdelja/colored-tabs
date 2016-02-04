package com.ivanferdelja.coloredtabs;


public class ShareItem {
    public int authorImageResource;
    public String title;
    public int imageResource;
    public String timestamp;

    public static ShareItem create(int authorImageResource, String title, int imageResource, String timestamp) {
        ShareItem story = new ShareItem();
        story.authorImageResource = authorImageResource;
        story.imageResource = imageResource;
        story.title = title;
        story.timestamp = timestamp;
        return story;
    }
}
