package com.fairdeal.dao;

import com.com.fairdeal.entity.UserRating;
import com.com.fairdeal.helper.JSONParser;
import com.com.fairdeal.helper.PostCall;
import com.com.fairdeal.helper.UserRatingDAOHelper;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Carlos Eduardo on 19/11/2015.
 */
public class UserRatingDAO implements InterfaceDAO<UserRating> {

    private String addUrl = "http://10.0.2.2/android_connect/user_rating/create_user_rating.php";
    private String removeUrl = "http://10.0.2.2/android_connect/user_rating/delete_user_rating.php";
    private String updateUrl = "http://10.0.2.2/android_connect/user_rating/update_user_rating.php";
    private String getUrl = "http://10.0.2.2/android_connect/user_rating/get_user_rating.php";
    private String getAllUrl = "http://10.0.2.2/android_connect/user_rating/get_all_user_ratings.php";
    private String countUserRatingsUrl = "http://10.0.2.2/android_connect/user_rating/count_user_ratings.php";
    private String userRatingAverageUrl = "http://10.0.2.2/android_connect/user_rating/user_rating_average.php";

    private JSONParser jsonParser;

    public UserRatingDAO(JSONParser jsonParser){
        this.jsonParser = jsonParser;
    }

    @Override
    public String add(UserRating userRating) {
        String id = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user_id", userRating.getUserId().toString()));
        params.add(new BasicNameValuePair("rating", userRating.getRating().getValue().toString()));

        try {
            JSONObject result = new PostCall(params, this.addUrl, this.jsonParser).execute().get();
            if(result != null) {
                id = result.getString("id");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean remove(Long id) {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", id.toString()));
        try {
            JSONObject result =  new PostCall(params, this.removeUrl, this.jsonParser).execute().get();
            if(result != null) {
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(UserRating userRating) {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", userRating.getId().toString()));
        params.add(new BasicNameValuePair("user_id", userRating.getUserId().toString()));
        params.add(new BasicNameValuePair("rating", userRating.getRating().getValue().toString()));

        try {
            JSONObject result =  new PostCall(params, this.updateUrl, this.jsonParser).execute().get();
            if(result != null){
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserRating get(Long id) {
        UserRating userRating = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", id.toString()));

        try {
            JSONObject result = new PostCall(params, this.getUrl, this.jsonParser).execute().get();
            if (result != null) {
                userRating = UserRatingDAOHelper.extractUserRatingFromJson(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userRating;
    }

    @Override
    public List<UserRating> getAll() {
        List<UserRating> userRatingList = null;
        List<NameValuePair> params = new ArrayList<>();
        try {
            JSONObject result = new PostCall(params, this.getAllUrl, this.jsonParser).execute().get();
            if(result != null){
                userRatingList = UserRatingDAOHelper.extractMultipleUserRatingsFromJson(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userRatingList;
    }

    public int countUserRatings(Long userId){
        int count = 0;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user_id", userId.toString()));
        try {
            JSONObject result =  new PostCall(params, this.countUserRatingsUrl, this.jsonParser).execute().get();
            if(result != null) {
                count = result.getInt("total");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return count;
    }

    public double getUserRatingAverage(Long userId){
        double average = 0;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user_id", userId.toString()));
        try {
            JSONObject result =  new PostCall(params, this.userRatingAverageUrl, this.jsonParser).execute().get();
            if(result != null) {
                average = result.getDouble("average");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return average;
    }
}
