package com.ivanferdelja.coloredtabs;

import com.squareup.otto.Bus;

public final class Application extends android.app.Application {

    Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();

        bus = new Bus();
    }

    public Bus getBus() {
        return bus;
    }
}
