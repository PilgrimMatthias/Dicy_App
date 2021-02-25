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

public class FourPlayerCounter extends AppCompatActivity {

    private int playerOneLife, playerTwoLife, playerThreeLife, playerFourLife;
    ArrayList<String> playersLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_player_counter);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView fpPlayerOneLife = (TextView) findViewById(R.id.FPplayerOneLife);
        TextView fpPlusOne = (TextView) findViewById(R.id.FPplusOne);
        TextView fpMinusOne = (TextView) findViewById(R.id.FPminusOne);

        TextView fpPlayerTwoLife = (TextView) findViewById(R.id.FPplayerTwoLife);
        TextView fpPlusTwo = (TextView) findViewById(R.id.FPplusTwo);
        TextView fpMinusTwo = (TextView) findViewById(R.id.FPminusTwo);

        TextView fpPlayerThreeLife = (TextView) findViewById(R.id.FPplayerThreeLife);
        TextView fpPlusThree = (TextView) findViewById(R.id.FPplusThree);
        TextView fpMinusThree = (TextView) findViewById(R.id.FPminusThree);

        TextView fpPlayerFourLife = (TextView) findViewById(R.id.FPplayerFourLife);
        TextView fpPlusFour = (TextView) findViewById(R.id.FPplusFour);
        TextView fpMinusFour = (TextView) findViewById(R.id.FPminusFour);

        Button ftpD = (Button) findViewById(R.id.btnFtpD);

        Life life = LifeCounter.life;

        playerOneLife = life.getLife();
        playerTwoLife = life.getLife();
        playerThreeLife = life.getLife();
        playerFourLife = life.getLife();

        fpPlayerOneLife.setText(String.valueOf(playerOneLife));
        fpPlayerTwoLife.setText(String.valueOf(playerTwoLife));
        fpPlayerThreeLife.setText(String.valueOf(playerThreeLife));
        fpPlayerFourLife.setText(String.valueOf(playerFourLife));

        try {
            Intent intent = getIntent();
            playersLife = intent.getStringArrayListExtra("Player life pass");
            fpPlayerOneLife.setText(playersLife.get(0));
            fpPlayerTwoLife.setText(playersLife.get(1));
            fpPlayerThreeLife.setText(playersLife.get(2));
            fpPlayerFourLife.setText(playersLife.get(3));
        }catch (NullPointerException e){
            Log.i("" + e, "");
        }

        //PLAYER ONE LIFE PLUS AND MINUS
        fpPlusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneLife++;
                fpPlayerOneLife.setText(String.valueOf(playerOneLife));
            }
        });
        fpMinusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneLife--;
                fpPlayerOneLife.setText(String.valueOf(playerOneLife));
            }
        });

        //PLAYER TWO LIFE PLUS AND MINUS
        fpPlusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTwoLife++;
                fpPlayerTwoLife.setText(String.valueOf(playerTwoLife));
            }
        });
        fpMinusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTwoLife--;
                fpPlayerTwoLife.setText(String.valueOf(playerTwoLife));
            }
        });

        //PLAYER THREE LIFE PLUS AND MINUS
        fpPlusThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerThreeLife++;
                fpPlayerThreeLife.setText(String.valueOf(playerThreeLife));
            }
        });
        fpMinusThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerThreeLife--;
                fpPlayerThreeLife.setText(String.valueOf(playerThreeLife));
            }
        });

        //PLAYER FOUR LIFE PLUS AND MINUS
        fpPlusFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerFourLife++;
                fpPlayerFourLife.setText(String.valueOf(playerFourLife));
            }
        });
        fpMinusFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerFourLife--;
                fpPlayerFourLife.setText(String.valueOf(playerFourLife));
            }
        });
        ftpD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lifeCounterActivity = new Intent(getApplicationContext(),LifeCounter.class);
                lifeCounterActivity.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                ArrayList<String> playersToSave = new ArrayList<String>();
                playersToSave.add(fpPlayerOneLife.getText().toString());
                playersToSave.add(fpPlayerTwoLife.getText().toString());
                playersToSave.add(fpPlayerThreeLife.getText().toString());
                playersToSave.add(fpPlayerFourLife.getText().toString());
                Bundle b = new Bundle();
                b.putStringArrayList("Player life", playersToSave);
                lifeCounterActivity.putExtras(b);
                startActivityIfNeeded(lifeCounterActivity, 0);
            }
        });
    }
}