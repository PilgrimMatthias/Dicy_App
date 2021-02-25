package com.example.dicyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class LifeCounter extends AppCompatActivity {

    private int players;
    public static Life life = new Life();
    ArrayList<String> savedPlayersLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_counter);

        Spinner spinPlayers = (Spinner) findViewById(R.id.spinPlayers);
        Spinner spinLife = (Spinner) findViewById(R.id.spinLife);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnContinue = (Button) findViewById(R.id.btnContinue);
        Button btnResume = (Button) findViewById(R.id.btnResume);

        Integer[] numOfPlayers = {2,3,4};
        Integer[] numOfLife = {10,20,25,30,35,40};

        Intent intent = getIntent();
        try {
            savedPlayersLife = intent.getStringArrayListExtra("Player life");
            Log.i("Players one life", "" + savedPlayersLife.get(0));
            btnResume.setVisibility(View.VISIBLE);
        }catch (NullPointerException e){
            Log.i("" + e, "");
        }

        ArrayAdapter<Integer> playersNumber = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, numOfPlayers);
        spinPlayers.setAdapter(playersNumber);
        spinPlayers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        players = 2;
                        break;
                    case 1:
                        players = 3;
                        break;
                    case 2:
                        players = 4;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast error = Toast.makeText(LifeCounter.this, "Please choose number of players!", Toast.LENGTH_SHORT);
            }
        });

        ArrayAdapter<Integer> lifeNumber = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, numOfLife);
        spinLife.setAdapter(lifeNumber);
        spinLife.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        life.setLife(10);
                        break;
                    case 1:
                        life.setLife(20);
                        break;
                    case 2:
                        life.setLife(25);
                        break;
                    case 3:
                        life.setLife(30);
                        break;
                    case 4:
                        life.setLife(35);
                        break;
                    case 5:
                        life.setLife(40);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast error = Toast.makeText(LifeCounter.this, "Please choose number of life!", Toast.LENGTH_SHORT);
            }
        });



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("players:", " = " + players);
                switch (players){
                    case 2:
                        Intent twoPlayer = new Intent(getApplicationContext(), TwoPlayerCounter.class);
                        startActivity(twoPlayer);
                        break;
                    case 3:
                        Intent threePlayer = new Intent(getApplicationContext(),ThreePlayerCounter.class);
                        startActivity(threePlayer);
                        break;
                    case 4:
                        Intent fourPlayer = new Intent(getApplicationContext(), FourPlayerCounter.class);
                        startActivity(fourPlayer);
                        break;
                }
            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueGame;
                int length = savedPlayersLife.toArray().length;
                switch(length){
                    case 2:
                        continueGame = new Intent(getApplicationContext(),TwoPlayerCounter.class);
                        Bundle two = new Bundle();
                        two.putStringArrayList("Player life pass",savedPlayersLife);
                        continueGame.putExtras(two);
                        startActivityIfNeeded(continueGame, 0);
                        break;
                    case 3:
                        continueGame = new Intent(getApplicationContext(), ThreePlayerCounter.class);
                        Bundle three = new Bundle();
                        three.putStringArrayList("Player life pass",savedPlayersLife);
                        continueGame.putExtras(three);
                        startActivityIfNeeded(continueGame, 0);
                        break;
                    case 4:
                        continueGame = new Intent(getApplicationContext(), FourPlayerCounter.class);
                        Bundle four = new Bundle();
                        four.putStringArrayList("Player life pass",savedPlayersLife);
                        continueGame.putExtras(four);
                        startActivityIfNeeded(continueGame, 0);
                        break;
                    default:
                        break;
                }
            }
        });

    }
}