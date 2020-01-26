package com.example.instagramcloneparse;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);   // Showing errors.

        Parse.initialize(new Parse.Configuration.Builder(this)
            .applicationId("0lUphS448H3JlWGBXN87AKtc6KTHrIRvaCZZR2Ez")
            .clientKey("kTLICbL8mZLQJve6kuZRBxwNjA1uEkA8T9pMwGVk")
            .server("https://parseapi.back4app.com/")
            .build()
        );
    }
}
