package com.example.dicyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public String beginRangeGet;
    public String endRangeGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDice = (Button) findViewById(R.id.btnDice);
        Button btnGenerator = (Button) findViewById(R.id.btnGenerator);
        Button btnCounter = (Button) findViewById(R.id.btnCounter);
        Button btnSettings = (Button) findViewById(R.id.btnSettings);

        try {
            Intent extras = getIntent();
            beginRangeGet = extras.getStringExtra("Begin range");
            Log.i("begin range", "" + beginRangeGet);
            endRangeGet = extras.getStringExtra("End range");
            Log.i("end range", "" + endRangeGet);
        } catch (NullPointerException e) {
            Log.i(e + "", "");
        }

        btnDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diceRollerActivity = new Intent(getApplicationContext(), DiceRoller.class);
                diceRollerActivity.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                startActivityIfNeeded(diceRollerActivity, 0);
            }
        });

        btnGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numberGeneratorActivity = new Intent(getApplicationContext(), NumberGenerator.class);
                numberGeneratorActivity.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                try {
                    numberGeneratorActivity.putExtra("Begin range get", beginRangeGet);
                    numberGeneratorActivity.putExtra("End range get", endRangeGet);
                }catch (NullPointerException e){
                    Log.i(e + "", "");
                }
                startActivityIfNeeded(numberGeneratorActivity, 0);
            }
        });

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lifeCounterActivity = new Intent(getApplicationContext(), LifeCounter.class);
                lifeCounterActivity.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                startActivityIfNeeded(lifeCounterActivity, 0);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}