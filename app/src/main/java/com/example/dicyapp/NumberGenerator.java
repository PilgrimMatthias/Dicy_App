package com.example.dicyapp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class NumberGenerator extends AppCompatActivity {

    public int number;
    public int beginRange = 0;
    public int endRange = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_generator);

        EditText beginRangeText = (EditText) findViewById(R.id.numberLow);
        EditText endRangeText = (EditText) findViewById(R.id.numberHigh);
        Button generateNumber = (Button) findViewById(R.id.btnGenerateNumber);
        TextView generatedNumber = (TextView) findViewById(R.id.generatedNumber);
        Button btnBack = (Button) findViewById(R.id.NGbtnBack);

        try {
            Intent extras = getIntent();
            beginRange = Integer.parseInt(extras.getStringExtra("Begin range get"));
            Log.i("begin range get", "" + beginRange);
            endRange = Integer.parseInt(extras.getStringExtra("End range get"));
        } catch (NumberFormatException e) {
            Log.i(e + "", "");
        }

        beginRangeText.setText(String.valueOf(beginRange));
        endRangeText.setText(String.valueOf(endRange));

        generateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beginRangeText.getText().toString().equals("")) {
                    beginRange = 0;
                } else beginRange = Integer.parseInt(beginRangeText.getText().toString());

                if (endRangeText.getText().toString().equals("")) {
                    endRange = 10;
                } else endRange = Integer.parseInt(endRangeText.getText().toString());

                if(beginRange > endRange){
                    Toast toast = Toast.makeText(getApplicationContext(),"Beginning number is higher than ending number!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }else {
                    number = numberGenerator(beginRange, endRange);
                    generatedNumber.setText(String.valueOf(number));
                }

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(NumberGenerator.this, MainActivity.class);
                mainActivity.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                mainActivity.putExtra("Begin range", beginRangeText.getText().toString());
                mainActivity.putExtra("End range", endRangeText.getText().toString());
                startActivity(mainActivity);
            }
        });

    }


    public int numberGenerator(int min, int max) {
        Random random = new Random();
        return (random.nextInt(max - min) + min);
    }
}