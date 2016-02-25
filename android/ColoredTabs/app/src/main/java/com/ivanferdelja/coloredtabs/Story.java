package com.ivanferdelja.coloredtabs;


public class Story {
    public int imageResource;
    public String headline;
    public boolean isVideo;

    public static Story create(int imageResource, String headline, boolean isVideo) {
        Story story = new Story();
        story.imageResource = imageResource;
        story.headline = headline;
        story.isVideo = isVideo;
        return story;
    }

    public static Story create(int imageResource, String headline) {
        return create(imageResource, headline, false);
    }
}
