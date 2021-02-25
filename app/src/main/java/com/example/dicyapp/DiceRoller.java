package com.example.dicyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class DiceRoller extends AppCompatActivity {
    public List<Dice> mDice;
    public DiceAdapter adapter;
    public RecyclerView rvDice;
    public SharedPreferences  mPrefs;

    private JSONSerializer mSerializer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller);

        Button btnSettings = (Button) findViewById(R.id.DCbtnSettings);
        Button roll = (Button) findViewById(R.id.DCbtnRoll);
        Button btnMainActivityBack = (Button) findViewById(R.id.DCbtnBack);

        //Serializer to handle loading notes
        mSerializer = new JSONSerializer("Dice.json", getApplicationContext());
        try {
            mDice = mSerializer.load();
            Log.i("mDice length", " " + mDice.toArray().length);
        }catch (Exception e){
            mDice = new ArrayList<Dice>();
            Log.e("Error loading notes: ", "", e );
        }

        //Recycler View
        rvDice = (RecyclerView) findViewById(R.id.DCdiceDisplay);
        adapter = new DiceAdapter(this,mDice);
        rvDice.setAdapter(adapter);
        rvDice.setLayoutManager(new LinearLayoutManager(this));


        //Adding dice from settings (choice)
        try {
            ArrayList<Integer> settDice = (ArrayList<Integer>) getIntent().getSerializableExtra("Dice arr");
            if (!settDice.isEmpty()){
                //Log.i("SettDiceArr implemented", " " + Arrays.toString(settDice.toArray()));
                for (int i = 0; i < settDice.toArray().length; i++){
                    int num = settDice.get(i);
                    Dice dice = new Dice(i);
                    addDice(new Dice(num));
                }
                settDice.clear();
            }
        }catch (NullPointerException e){
            Log.i("Null pointer Exception", "");
        }

        //Removing dice
        try {
            ArrayList<Integer> removeDice = (ArrayList<Integer>) getIntent().getSerializableExtra("Remove arr");
            if (!removeDice.isEmpty()) {
                //Log.i("RemDiceArr implemented", " " + Arrays.toString(removeDice.toArray()));
                for (int i = 0; i < removeDice.toArray().length; i++) {
                    removeDice(removeDice.get(i));
                }
                removeDice.clear();
            }
        }catch (NullPointerException e){
            Log.i("Null pointer Exception", "");
        }

        //roll dice
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0 ; i < mDice.toArray().length; i++){
                    mDice.get(i).rollDice();
                    adapter.notifyItemChanged(i);
                }
            }
        });

        //launch settings
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dcSettings = new Intent(getApplicationContext(), DiceRoller_Settings.class);
                startActivity(dcSettings);
            }
        });

        //back to main screen
        btnMainActivityBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityBack = new Intent(getApplicationContext(), MainActivity.class);
                mainActivityBack.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                startActivityIfNeeded(mainActivityBack, 0);
            }
        });
    }

    public void addDice (Dice dice){
        mDice.add(dice);
        adapter.notifyDataSetChanged();
    }

    public void removeDice(int diceToRemove){
        try {
            int diceIndex = 0;
            for (int i = 0; i < mDice.toArray().length; i++){
                if (mDice.get(i).getNumOfLoop() == diceToRemove){
                    diceIndex = i;
                }
            }
            mDice.remove(diceIndex);
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            Log.i("Exception: ", "" + e);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("mDice length", " " + mDice.toArray().length);
        saveDice();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("mDice length", " " + mDice.toArray().length);
        saveDice();
    }

    public void saveDice(){
        try {
            mSerializer.save(mDice);
        }catch (Exception e){
            Log.e("Error saving dice: ", "", e );
        }
    }

}
