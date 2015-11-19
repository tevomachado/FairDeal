package com.com.fairdeal.helper;

import com.com.fairdeal.entity.Email;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Eduardo on 18/11/2015.
 */
public class EmailDAOHelper {

    public static Email extractEmailFromJson(JSONObject jsonObject) throws JSONException {
        JSONArray emailArray = jsonObject.getJSONArray("email");
        JSONObject emailObject = emailArray.getJSONObject(0);
        Email email = new Email();
        email.setId(emailObject.getLong("id"));
        email.setUserId(emailObject.getLong("user_id"));
        email.setEmail(emailObject.getString("email"));
        return email;
    }

    public static List<Email> extractMultipleEmailsFromJson(JSONObject jsonObject) throws JSONException {
        JSONArray emailsArray = jsonObject.getJSONArray("emails");
        List<Email> emailList = new ArrayList<>();
        for(int i = 0; i < emailsArray.length(); i++){
            JSONObject emailObject = emailsArray.getJSONObject(i);
            Email email = new Email();
            email.setId(emailObject.getLong("id"));
            email.setUserId(emailObject.getLong("user_id"));
            email.setEmail(emailObject.getString("email"));
            emailList.add(email);
        }
        return emailList;
    }
}
