package com.example.dicyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiceRoller_Settings extends AppCompatActivity {

    public ArrayList<Integer> settDice = new ArrayList<Integer>();
    public ArrayList<Integer> removeDice = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller__settings);

        TextView dcSetList = (TextView) findViewById(R.id.DCSetList);

        Button dcSettBack = (Button) findViewById(R.id.btnDCSetBack);
        Button btnK4Add = (Button) findViewById(R.id.k4BtnAdd);
        Button btnK4Remove = (Button) findViewById(R.id.K4BtnRemove);
        Button btnK6Add = (Button) findViewById(R.id.k6BtnAdd);
        Button btnK6Remove = (Button) findViewById(R.id.k6BtnRemove);
        Button btnK8Add = (Button) findViewById(R.id.K8BtnAdd);
        Button btnK8Remove = (Button) findViewById(R.id.K8BtnRemove);
        Button btnK10Add = (Button) findViewById(R.id.K10BtnAdd);
        Button btnK10Remove = (Button) findViewById(R.id.K10BtnRemove);
        Button btnK12Add = (Button) findViewById(R.id.K12BtnAdd);
        Button btnK12Remove = (Button) findViewById(R.id.K12BtnRemove);
        Button btnK20Add = (Button) findViewById(R.id.K20BtnAdd);
        Button btnK20Remove = (Button) findViewById(R.id.K20BtnRemove);

        //Add type of cube
        btnK4Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settDice.add(4);
                dcSetList.append(4 + ", ");
            }
        });
        btnK6Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settDice.add(6);
                dcSetList.append(6 + ", ");
            }
        });
        btnK8Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settDice.add(8);
                dcSetList.append(8 + ", ");
            }
        });
        btnK10Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settDice.add(10);
                dcSetList.append(10 + ", ");
            }
        });
        btnK12Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settDice.add(12);
                dcSetList.append(12 + ", ");
            }
        });
        btnK20Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settDice.add(20);
                dcSetList.append(20 + ", ");
            }
        });

        //Remove type of cube
        btnK4Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDice.add(4);
            }
        });
        btnK6Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDice.add(6);
            }
        });
        btnK8Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDice.add(8);
            }
        });
        btnK10Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDice.add(10);
            }
        });
        btnK12Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDice.add(12);
            }
        });
        btnK20Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDice.add(20);
            }
        });


        //Back button to Dice Roller
        dcSettBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diceRollerBack = new Intent(DiceRoller_Settings.this, DiceRoller.class);
                diceRollerBack.putExtra("Dice arr", settDice);
                diceRollerBack.putExtra("Remove arr", removeDice);
                diceRollerBack.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                Log.i("Sett dice array list", ": " + Arrays.toString(settDice.toArray()));
                startActivityIfNeeded(diceRollerBack, 0);
            }
        });
    }

}