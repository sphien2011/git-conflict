package com.example.sphien2011.datarealmcontact.common.model;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

/**
 * Created by sphien2011 on 21/10/2016.
 */
public class ApplicationWeev extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Realm.init(mContext);
    }

    public static Context getContext() {
        return mContext;
    }
}
