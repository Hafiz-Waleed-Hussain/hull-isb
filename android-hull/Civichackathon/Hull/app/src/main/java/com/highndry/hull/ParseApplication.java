package com.highndry.hull;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

public class ParseApplication extends Application {

    private static final String YOUR_APPLICATION_ID = "evTJMDPOKrYSzmbl1n29fdkrf4hxH3auipbVxwIC";
    private static final String YOUR_CLIENT_KEY = "5kVooJSVArSqhvO9Lv0lBlJ4V5nBZ4UTSMqdo5Ob";

    @Override
  public void onCreate() {
    super.onCreate();

    // Initialize Crash Reporting.
    ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
    ParseFacebookUtils.initialize("1538230953124230");


    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }
}
