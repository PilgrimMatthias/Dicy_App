package com.example.dicyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TwoPlayerCounter extends AppCompatActivity {

    private int playerOneLife, playerTwoLife;
    ArrayList<String> playersLife;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_counter);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        TextView plusOne = (TextView) findViewById(R.id.plusOne);
        TextView plusTwo = (TextView) findViewById(R.id.plusTwo);
        TextView minusOne = (TextView) findViewById(R.id.minusOne);
        TextView minusTwo = (TextView) findViewById(R.id.minusTwo);
        TextView playerOne = (TextView) findViewById(R.id.playerOneLife);
        TextView playerTwo = (TextView) findViewById(R.id.playerTwoLife);

        Life life = LifeCounter.life;

        playerOneLife = life.getLife();
        playerTwoLife = life.getLife();

        playerOne.setText(String.valueOf(playerOneLife));
        playerTwo.setText(String.valueOf(playerTwoLife));

        try {
            Intent intent = getIntent();
            playersLife = intent.getStringArrayListExtra("Player life pass");
            playerOne.setText(playersLife.get(0));
            playerTwo.setText(playersLife.get(1));
        }catch (NullPointerException e){
            Log.i("" + e, "");
        }

        plusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneLife++;
                playerOne.setText(String.valueOf(playerOneLife));
            }
        });

        minusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneLife--;
                playerOne.setText(String.valueOf(playerOneLife));
            }
        });

        plusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTwoLife++;
                playerTwo.setText(String.valueOf(playerTwoLife));
            }
        });

        minusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTwoLife--;
                playerTwo.setText(String.valueOf(playerTwoLife));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lifeCounterActivity = new Intent(getApplicationContext(),LifeCounter.class);
                lifeCounterActivity.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                ArrayList<String> playersToSave = new ArrayList<String>();
                playersToSave.add(playerOne.getText().toString());
                playersToSave.add(playerTwo.getText().toString());
                Bundle b = new Bundle();
                b.putStringArrayList("Player life", playersToSave);
                lifeCounterActivity.putExtras(b);
                startActivityIfNeeded(lifeCounterActivity, 0);
            }
        });
    }
}