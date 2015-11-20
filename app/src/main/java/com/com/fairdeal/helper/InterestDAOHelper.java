package com.com.fairdeal.helper;

import com.com.fairdeal.entity.Interest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Eduardo on 19/11/2015.
 */
public class InterestDAOHelper {

    public static Interest extractInterestFromJson(JSONObject jsonObject) throws JSONException {
        JSONArray interestArray = jsonObject.getJSONArray("interest");
        JSONObject interestObject = interestArray.getJSONObject(0);
        Interest interest = new Interest();
        interest.setId(interestObject.getLong("id"));
        interest.setAnnouncementId(interestObject.getLong("announcement_id"));
        interest.setUserId(interestObject.getLong("user_id"));
        return interest;
    }

    public static List<Interest> extractMultipleInterestsFromJson(JSONObject jsonObject) throws JSONException {
        JSONArray interestsArray = jsonObject.getJSONArray("interests");
        List<Interest> interestList = new ArrayList<>();
        for(int i = 0; i < interestsArray.length(); i++){
            JSONObject interestObject = interestsArray.getJSONObject(i);
            Interest interest = new Interest();
            interest.setId(interestObject.getLong("id"));
            interest.setAnnouncementId(interestObject.getLong("announcement_id"));
            interest.setUserId(interestObject.getLong("user_id"));
            interestList.add(interest);
        }
        return interestList;
    }

}
