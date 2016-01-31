package com.ivanferdelja.coloredtabs;


public class Story {
    public int imageResource;
    public String description;
    public String headline;

    public static Story create(int imageResource, String description, String headline) {
        Story story = new Story();
        story.description = description;
        story.imageResource = imageResource;
        story.headline = headline;
        return story;
    }
}
