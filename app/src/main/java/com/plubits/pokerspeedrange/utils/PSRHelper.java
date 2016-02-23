package com.plubits.pokerspeedrange.utils;

import android.app.Activity;

import com.plubits.pokerspeedrange.models.TableValue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.plubits.pokerspeedrange.utils.Constants.*;

/**
 * Created by Marc on 30/1/2016.
 */
public class PSRHelper {
    public static String loadJSONFromAsset(Activity activity) {
        String json = null;
        JSONObject jsonObject = null;
        try {
            InputStream is = activity.getAssets().open(JSON_FILENAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            //jsonObject = new JSONObject(json);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    private static String[] jsonArray2StringArray (JSONArray jsonArray){
            String[] stringArray = null;
            int length = jsonArray.length();
            if(jsonArray!=null){
                stringArray = new String[length];
                for(int i=0;i<length;i++){
                    stringArray[i]= jsonArray.optString(i);
                }
            }
            return stringArray;
    }

    public static String[] getList(JSONObject json, String tabID, String listID){
        JSONArray positionsArray = null;
        try {
            JSONObject pushObject = json.getJSONObject(tabID);
            positionsArray = pushObject.getJSONArray(listID);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return PSRHelper.jsonArray2StringArray(positionsArray);
    }

    public static ArrayList<TableValue> getTable(JSONObject json){
        ArrayList<TableValue> tableValues = new ArrayList<TableValue>();
        JSONArray tableArray = null;
        try {
            tableArray = json.getJSONArray(TABLE_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String[] valuesID = PSRHelper.jsonArray2StringArray(tableArray);
        for (String valueID : valuesID) {
            tableValues.add(new TableValue(valueID, VALUES_STATES.UNSELECTED));
        }
        return tableValues;
    }

    public static ArrayList<String[]> getValues(JSONObject json, String tabID, String positionID, String blindID, String percentID, boolean antes){
        JSONArray tableArray = null;
        ArrayList<String[]> values = new ArrayList<String[]>();
        try {
            JSONObject jsonValues = json.getJSONObject(tabID);
            jsonValues = jsonValues.getJSONObject(TABLE_VALUES_KEY);
            jsonValues = jsonValues.getJSONObject(positionID);
            jsonValues = jsonValues.getJSONObject(blindID);
            if(!percentID.equals("")) jsonValues = jsonValues.getJSONObject(percentID);
            String lastKey = antes ? ANTES_KEY : NO_ANTES_KEY;
            JSONArray jsonIntervals = jsonValues.getJSONArray(lastKey);

            int length = jsonIntervals.length();
            if(jsonIntervals!=null){
                //stringArray = new String[length];
                for(int i=0;i<length;i++){
                    JSONArray interval = jsonIntervals.getJSONArray(i);
                    values.add(PSRHelper.jsonArray2StringArray(interval));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return values;
    }
}
