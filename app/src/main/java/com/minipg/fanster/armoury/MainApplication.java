package com.minipg.fanster.armoury;

import android.app.Application;

import com.minipg.fanster.armoury.manager.bus.Contextor;


/**
 * Created by Knot on 8/4/2017.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize
        Contextor.getInstance().init(getApplicationContext());
    }
}
