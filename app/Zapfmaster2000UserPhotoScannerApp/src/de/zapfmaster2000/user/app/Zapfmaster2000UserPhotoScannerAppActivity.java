package de.zapfmaster2000.user.app;

import org.apache.cordova.DroidGap;

import android.os.Bundle;

public class Zapfmaster2000UserPhotoScannerAppActivity extends DroidGap {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        super.loadUrl("file:///android_asset/www/index.html");
    }
}