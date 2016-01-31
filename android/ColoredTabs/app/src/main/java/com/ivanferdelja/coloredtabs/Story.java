package com.ivanferdelja.coloredtabs;


public class Story {
    public int imageResource;
    public String description;

    public static Story create(int imageResource, String description) {
        Story story = new Story();
        story.description = description;
        story.imageResource = imageResource;
        return story;
    }
}
