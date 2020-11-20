package com.example.sendbirdbooks;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration =
                new RealmConfiguration.Builder().name("BookApp")
                        .schemaVersion(1)
                        .allowWritesOnUiThread(true)
                        .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
