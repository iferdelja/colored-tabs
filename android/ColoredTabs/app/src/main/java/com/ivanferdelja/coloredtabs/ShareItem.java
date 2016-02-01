package com.ivanferdelja.coloredtabs;


public class ShareItem {
    public int authorImageResource;
    public String title;
    public int imageResource;

    public static ShareItem create(int authorImageResource, String title, int imageResource) {
        ShareItem story = new ShareItem();
        story.authorImageResource = authorImageResource;
        story.imageResource = imageResource;
        story.title = title;
        return story;
    }
}
