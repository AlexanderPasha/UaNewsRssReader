package com.mannydev.rssalluanews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

/**
 * Created by manny on 17.09.17.
 */

public class SplashActivity extends AppCompatActivity {

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        intent = new Intent(this, MainActivity.class);


        startActivity(intent);
        finish();
    }
}
