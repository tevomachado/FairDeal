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
        JSONObject userObject = userArray.getJSONObject(0);
        User user = new User();
        user.setId(userObject.getLong("id"));
        user.setStagNumber(userObject.getString("stag_number"));
        user.setDateOfBirth(DateHelper.stringToDate(userObject.getString("date_of_birth")));
        user.setBuildingId(userObject.getLong("building_id"));
        user.setPhoneNumber(userObject.getString("phone_number"));
        user.setFirstName(userObject.getString("first_name"));
        user.setLastName(userObject.getString("last_name"));
        user.setFbID(userObject.getString("fb_id"));
        return user;
    }

    public static List<User> extractMultipleUsersFromJson(JSONObject jsonObject) throws JSONException, ParseException {
        JSONArray usersArray = jsonObject.getJSONArray("users");
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < usersArray.length(); i++){
            JSONObject userObject = usersArray.getJSONObject(i);
            User user = new User();
            user.setId(userObject.getLong("id"));
            user.setStagNumber(userObject.getString("stag_number"));
            user.setDateOfBirth(DateHelper.stringToDate(userObject.getString("date_of_birth")));
            user.setBuildingId(userObject.getLong("building_id"));
            user.setPhoneNumber(userObject.getString("phone_number"));
            user.setFirstName(userObject.getString("first_name"));
            user.setLastName(userObject.getString("last_name"));
            user.setFbID(userObject.getString("fb_id"));
            userList.add(user);
        }
        return userList;
    }
}
