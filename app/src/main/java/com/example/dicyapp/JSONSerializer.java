package com.example.dicyapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONSerializer {
    private String mFileName;
    private Context mContext;

    public JSONSerializer(String fn, Context con){
        mFileName = fn;
        mContext = con;
    }

    public void save(List<Dice> dice) throws JSONException, IOException {
        JSONArray jArray = new JSONArray();
        for (Dice n : dice){
            jArray.put(n.convertToJSON());
            Writer writer = null;

            try{
                OutputStream out = mContext.openFileOutput(mFileName,mContext.MODE_PRIVATE);

                writer = new OutputStreamWriter(out);
                writer.write(jArray.toString());
            }finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    public ArrayList<Dice> load() throws IOException, JSONException{
        ArrayList<Dice> diceList = new ArrayList<Dice>();
        BufferedReader reader = null;
        try{
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                jsonString.append(line);
            }

            JSONArray jArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < jArray.length(); i++){
                diceList.add(new Dice(jArray.getJSONObject(i)));
            }
        }catch (FileNotFoundException e){

        }finally {
            if (reader != null){
                reader.close();
            }
        }
        return diceList;
    }
}
