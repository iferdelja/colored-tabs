package com.ivanferdelja.coloredtabs;

public final class Application extends android.app.Application {

    BitmapManager bitmapManager;

    @Override
    public void onCreate() {
        super.onCreate();

        bitmapManager = new BitmapManager();
    }

    public BitmapManager getBitmapManager() {
        return bitmapManager;
    }
}
