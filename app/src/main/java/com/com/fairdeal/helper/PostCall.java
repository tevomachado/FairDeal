package com.com.fairdeal.helper;

import android.content.Entity;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Carlos Eduardo on 16/11/2015.
 */
public class PostCall extends AsyncTask<String, String, JSONObject> {

    private  List<NameValuePair> params;
    private String target;
    private JSONParser jsonParser;


    public PostCall(List<NameValuePair> params, String target, JSONParser jsonParser){
        this.params = params;
        this.target = target;
        this.jsonParser= jsonParser;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject jsonObject = jsonParser.makeHttpRequest(this.target, "POST", this.params);
        try{
            String success = jsonObject.get(jsonParser.TAG_SUCCESS).toString();

            if (success.equals("1")) {
                return jsonObject;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
