package com.example.dicyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreePlayerCounter extends AppCompatActivity {

    private int playerOneLife, playerTwoLife, playerThreeLife;
    ArrayList<String> playersLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_player_counter);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView tpPlayerOne = (TextView) findViewById(R.id.TPplayerOneLife);
        TextView tpPlusOne = (TextView) findViewById(R.id.TPplusOne);
        TextView tpMinusOne = (TextView) findViewById(R.id.TPminusOne);
        TextView tpPlayerTwo = (TextView) findViewById(R.id.TPplayerTwoLife);
        TextView tpPlusTwo = (TextView) findViewById(R.id.TPplusTwo);
        TextView tpMinusTwo = (TextView) findViewById(R.id.TPminusTwo);
        TextView tpPlayerThree = (TextView) findViewById(R.id.TPplayerThreeLife);
        TextView tpPlusThree = (TextView) findViewById(R.id.TPplusThree);
        TextView tpMinusThree = (TextView) findViewById(R.id.TPminusThree);
        Button btnD = (Button) findViewById(R.id.btnD);

        Life life = LifeCounter.life;

        playerOneLife = life.getLife();
        playerTwoLife = life.getLife();
        playerThreeLife = life.getLife();

        tpPlayerOne.setText(String.valueOf(playerOneLife));
        tpPlayerTwo.setText(String.valueOf(playerTwoLife));
        tpPlayerThree.setText(String.valueOf(playerThreeLife));

        try {
            Intent intent = getIntent();
            playersLife = intent.getStringArrayListExtra("Player life pass");
            tpPlayerOne.setText(playersLife.get(0));
            tpPlayerTwo.setText(playersLife.get(1));
            tpPlayerThree.setText(playersLife.get(2));
        }catch (NullPointerException e){
            Log.i("" + e, "");
        }

        //PLAYER ONE LIFE PLUS AND MINUS
        tpPlusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneLife++;
                tpPlayerOne.setText(String.valueOf(playerOneLife));
            }
        });
        tpMinusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneLife--;
                tpPlayerOne.setText(String.valueOf(playerOneLife));
            }
        });

        //PLAYER TWO LIFE PLUS AND MINUS
        tpPlusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTwoLife++;
                tpPlayerTwo.setText(String.valueOf(playerTwoLife));
            }
        });
        tpMinusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTwoLife--;
                tpPlayerTwo.setText(String.valueOf(playerTwoLife));
            }
        });

        //PLAYER THREE LIFE PLUS AND MINUS
        tpPlusThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerThreeLife++;
                tpPlayerThree.setText(String.valueOf(playerThreeLife));
            }
        });
        tpMinusThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerThreeLife--;
                tpPlayerThree.setText(String.valueOf(playerThreeLife));
            }
        });


        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lifeCounterActivity = new Intent(getApplicationContext(),LifeCounter.class);
                lifeCounterActivity.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                ArrayList<String> playersToSave = new ArrayList<String>();
                playersToSave.add(tpPlayerOne.getText().toString());
                playersToSave.add(tpPlayerTwo.getText().toString());
                playersToSave.add(tpPlayerThree.getText().toString());
                Bundle b = new Bundle();
                b.putStringArrayList("Player life", playersToSave);
                lifeCounterActivity.putExtras(b);
                startActivityIfNeeded(lifeCounterActivity, 0);
            }
        });
    }
}