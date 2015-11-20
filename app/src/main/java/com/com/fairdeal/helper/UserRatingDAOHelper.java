package com.com.fairdeal.helper;

import com.com.fairdeal.entity.Rating;
import com.com.fairdeal.entity.UserRating;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Eduardo on 19/11/2015.
 */
public class UserRatingDAOHelper {

    public static UserRating extractUserRatingFromJson(JSONObject jsonObject) throws JSONException {
        JSONArray userRatingArray = jsonObject.getJSONArray("user_rating");
        JSONObject userObject = userRatingArray.getJSONObject(0);
        UserRating userRating = new UserRating();
        userRating.setId(userObject.getLong("id"));
        userRating.setUserId(userObject.getLong("user_id"));
        userRating.setRating(getRatingFromValue(userObject.getString("rating")));
        return userRating;
    }

    public static List<UserRating> extractMultipleUserRatingsFromJson(JSONObject jsonObject) throws JSONException {
        JSONArray ratingsArray = jsonObject.getJSONArray("user_ratings");
        List<UserRating> ratingsList = new ArrayList<>();
        for(int i = 0; i < ratingsArray.length(); i++){
            JSONObject userObject = ratingsArray.getJSONObject(i);
            UserRating userRating = new UserRating();
            userRating.setId(userObject.getLong("id"));
            userRating.setUserId(userObject.getLong("user_id"));
            userRating.setRating(getRatingFromValue(userObject.getString("rating")));
            ratingsList.add(userRating);
        }
        return ratingsList;
    }

    public static Rating getRatingFromValue(String value){
        switch (value){
            case "1": return Rating.BAD;
            case "2": return Rating.MEDIUM;
            case "3": return Rating.GOOD;
            case "4": return Rating.EXCELENT;
            case "5": return Rating.AWESOME;
            default : return null;
        }
    }
}
