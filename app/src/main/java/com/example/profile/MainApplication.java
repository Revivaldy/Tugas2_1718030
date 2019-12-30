package com.example.profile;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalHellper.OnAttach(base, "en"));
    }
}
