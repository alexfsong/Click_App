package com.songalexander.clickapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView tr;
    TextView tl;
    TextView br;
    TextView bl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        tr = findViewById(R.id.TopRight);
        tl = findViewById(R.id.TopLeft);
        bl = findViewById(R.id.BottomLeft);
        br = findViewById(R.id.BottomRight);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor e = sp.edit();
        e.putInt("font", 8);
        // seek bar
        SeekBar font = findViewById(R.id.FontSize);
        font.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int size = sp.getInt("font", 12) + progress/3;
                SharedPreferences.Editor e = sp.edit();
                e.putInt("font", size);
                tr.setTextSize(size);
                tl.setTextSize(size);
                bl.setTextSize(size);
                br.setTextSize(size);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // set preferences for each textView

        e.putInt("trCount", 0);
        e.putInt("tlCount", 0);
        e.putInt("brCount", 0);
        e.putInt("blCount", 0);
        e.apply();
        View.OnClickListener jaunt = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor e = sp.edit();
                int numclicks;
                String text;
                if(v == tr) {
                    numclicks = sp.getInt("trCount", 0) + 1;
                    text = "Top Right: " + numclicks;
                    e.putInt("trCount", numclicks);
                }
                else if(v == tl) {
                    numclicks = sp.getInt("tlCount", 0) + 1;
                    text = "Top Left: " + numclicks;
                    e.putInt("tlCount", numclicks);
                }
                else if(v == br) {
                    numclicks = sp.getInt("brCount", 0) + 1;
                    text = "Bottom Right: " + numclicks;
                    e.putInt("brCount", numclicks);
                }
                else {
                    numclicks = sp.getInt("blCount", 0) + 1;
                    text = "Bottom Left: " + numclicks;
                    e.putInt("blCount", numclicks);
                }
                e.apply();

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        };
        tr.setOnClickListener(jaunt);
        tl.setOnClickListener(jaunt);
        br.setOnClickListener(jaunt);
        bl.setOnClickListener(jaunt);


    }
}
