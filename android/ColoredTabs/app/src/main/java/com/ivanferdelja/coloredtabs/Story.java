package com.ivanferdelja.coloredtabs;


public class Story {
    public int imageResource;
    public String headline;

    public static Story create(int imageResource, String headline) {
        Story story = new Story();
        story.imageResource = imageResource;
        story.headline = headline;
        return story;
    }
}
