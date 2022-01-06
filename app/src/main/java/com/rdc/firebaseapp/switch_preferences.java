package com.rdc.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;

public class switch_preferences extends AppCompatActivity {

    SwitchCompat switchCompat;
    ImageView imageView;

    private static String MY_PREFS = "switch_prefs";
    private static String LIGHT_STAT = "light_on";
    private static String SWITCH_STAT = "switch_status";

    boolean switch_status;
    boolean light_status;
    SharedPreferences myPref;
    SharedPreferences.Editor myEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_activity);

        switchCompat = findViewById(R.id.sw1);
        imageView = findViewById(R.id.img);
        myPref = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        myEditor = getSharedPreferences(MY_PREFS, MODE_PRIVATE).edit();
        switch_status = myPref.getBoolean(SWITCH_STAT, false);
        light_status = myPref.getBoolean(SWITCH_STAT, false);

        switchCompat.setChecked(switch_status);

        if(light_status){
            imageView.setImageResource(R.drawable.ic_lamp_on);
        }else{
            imageView.setImageResource(R.drawable.ic_lamp_off);
        }

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    imageView.setImageResource(R.drawable.ic_lamp_on);
                    myEditor.putBoolean(SWITCH_STAT, true);
                    myEditor.putBoolean(LIGHT_STAT, true);
                    myEditor.apply();
                    switchCompat.setChecked(true);
                }else{
                    imageView.setImageResource(R.drawable.ic_lamp_off);
                    myEditor.putBoolean(SWITCH_STAT, false);
                    myEditor.putBoolean(LIGHT_STAT, false);
                    myEditor.apply();
                    switchCompat.setChecked(false);
                }
            }
        });
    }
}