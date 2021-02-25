package com.example.dicyapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class Dice {
    private String name;
    private int cubeLoops;

    public Dice(int numOfLoop){
        cubeLoops = numOfLoop;
    }

    public void setName(){ this.name = name; }

    public String getName() {
        return name;
    }

    public int getNumOfLoop() {
        return cubeLoops;
    }

    public void setNumOfLoop(int cubeLoops) {
        this.cubeLoops = cubeLoops;
    }

    public int rollDice(){
        int result = 0;
        Random random = new Random();
        switch(cubeLoops){
            case 4:
                result = random.nextInt(4) + 1;
                break;
            case 6:
                result = random.nextInt(6) + 1;
                break;
            case 8:
                result = random.nextInt(8) + 1;
                break;
            case 10:
                result = random.nextInt(10) + 1;
                break;
            case 12:
                result = random.nextInt(12) + 1;
                break;
            case 20:
                result = random.nextInt(20) + 1;
                break;
        }
        return result;
    }

    //JSON
    private static final String JSON_DICE_NAME = "dice name";
    private static final String JSON_NUM_OF_LOOPS = "num of loops";

    public Dice(JSONObject jo) throws JSONException{
        cubeLoops = jo.getInt(JSON_NUM_OF_LOOPS);
        name = Integer.toString(cubeLoops);
    }

    public JSONObject convertToJSON() throws JSONException{
        JSONObject jo = new JSONObject();

        jo.put(JSON_DICE_NAME,name);
        jo.put(JSON_NUM_OF_LOOPS,cubeLoops);
        return jo;
    }


}
