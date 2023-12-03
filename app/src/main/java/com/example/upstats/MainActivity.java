package com.example.upstats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String tmp = "test string";
    long foregroundStartTime, backgroundStartTime;
    long foregroundElapsedTime;

    long foregroundTime, backgroundTime;

    Boolean isFirstBoot = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        foregroundStartTime = System.nanoTime();

        // Background
        TextView textView = (TextView) findViewById(R.id.textView_BackgroundTime);
        if(!isFirstBoot)
        {
            backgroundTime += (System.nanoTime() - backgroundStartTime);
            textView.setText((double) backgroundTime / 1_000_000_000 + " seconds");
        }
        isFirstBoot = false;

        Toast.makeText(this, "Now in Foreground", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        // Foreground
        TextView textView = (TextView) findViewById(R.id.textView_ForegroundTime);
        foregroundTime += (System.nanoTime() - foregroundStartTime);
        textView.setText((double) foregroundTime / 1_000_000_000 + " seconds");

        backgroundStartTime = System.nanoTime();

        Toast.makeText(this, "Now in Background", Toast.LENGTH_SHORT).show();
    }
}