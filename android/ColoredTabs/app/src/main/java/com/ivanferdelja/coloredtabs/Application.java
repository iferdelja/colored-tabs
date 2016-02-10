package com.ivanferdelja.coloredtabs;

public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FontsOverride.setDefaultFont(this, "sans-serif", "roboto-blackitalic.ttf");
    }
}
