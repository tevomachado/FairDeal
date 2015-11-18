package com.com.fairdeal.helper;

import com.com.fairdeal.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Carlos Eduardo on 17/11/2015.
 */
public class UserDAOHelper {

    public static User extractUserFromJson(JSONObject jsonObject) throws JSONException, ParseException {
        JSONArray userArray = jsonObject.getJSONArray("user");
        User user = new User();
        user.setId(userArray.getJSONObject(0).getLong("id"));
        user.setStagNumber(userArray.getJSONObject(0).getString("stag_number"));
        user.setDateOfBirth(stringToDate(userArray.getJSONObject(0).getString("date_of_birth")));
        user.setBuildingId(userArray.getJSONObject(0).getLong("building_id"));
        user.setPhoneNumber(userArray.getJSONObject(0).getString("phone_number"));
        user.setFirstName(userArray.getJSONObject(0).getString("first_name"));
        user.setLastName(userArray.getJSONObject(0).getString("last_name"));
        user.setFbID(userArray.getJSONObject(0).getString("fb_id"));
        return user;
    }

    public static List<User> extractMultipleUsersFromJson(JSONObject jsonObject) throws JSONException, ParseException {
        JSONArray usersArray = jsonObject.getJSONArray("users");
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < usersArray.length(); i++){
            User user = new User();
            user.setId(usersArray.getJSONObject(i).getLong("id"));
            user.setStagNumber(usersArray.getJSONObject(0).getString("stag_number"));
            user.setDateOfBirth(stringToDate(usersArray.getJSONObject(0).getString("date_of_birth")));
            user.setBuildingId(usersArray.getJSONObject(0).getLong("building_id"));
            user.setPhoneNumber(usersArray.getJSONObject(0).getString("phone_number"));
            user.setFirstName(usersArray.getJSONObject(0).getString("first_name"));
            user.setLastName(usersArray.getJSONObject(0).getString("last_name"));
            user.setFbID(usersArray.getJSONObject(0).getString("fb_id"));
            userList.add(user);
        }
        return userList;
    }

    public static String dateToDbFormat(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date stringToDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }


}
