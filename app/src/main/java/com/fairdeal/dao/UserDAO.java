package com.fairdeal.dao;

import android.content.SyncStatusObserver;

import com.com.fairdeal.entity.User;
import com.com.fairdeal.helper.JSONParser;
import com.com.fairdeal.helper.PostCall;
import com.com.fairdeal.helper.UserDAOHelper;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Carlos Eduardo on 16/11/2015.
 */
public class UserDAO implements InterfaceDAO<User> {

    private String addUrl = "http://10.0.2.2/android_connect/user/create_user.php";
    private String removeUrl = "http://10.0.2.2/android_connect/user/delete_user.php";
    private String updateUrl = "http://10.0.2.2/android_connect/user/update_user.php";
    private String getUrl = "http://10.0.2.2/android_connect/user/get_user_details.php";
    private String getAllUrl = "http://10.0.2.2/android_connect/user/get_all_users.php";



    private JSONParser jsonParser;

    public UserDAO(JSONParser jsonParser){
        this.jsonParser = jsonParser;
    }

    @Override
    public String add(User user) {
        String id = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("first_name", user.getFirstName()));
        params.add(new BasicNameValuePair("last_name", user.getLastName()));
        params.add(new BasicNameValuePair("phone_number", user.getPhoneNumber()));
        params.add(new BasicNameValuePair("building_id", user.getBuildingId().toString()));
        params.add(new BasicNameValuePair("stag_number", user.getStagNumber().toString()));
        params.add(new BasicNameValuePair("date_of_birth", UserDAOHelper.dateToDbFormat(user.getDateOfBirth())));
        params.add(new BasicNameValuePair("fb_id", user.getFbID()));

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
    public boolean update(User user) {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", user.getId().toString()));
        params.add(new BasicNameValuePair("first_name", user.getFirstName()));
        params.add(new BasicNameValuePair("last_name", user.getLastName()));
        params.add(new BasicNameValuePair("phone_number", user.getPhoneNumber()));
        params.add(new BasicNameValuePair("building_id", user.getBuildingId().toString()));
        params.add(new BasicNameValuePair("stag_number", user.getStagNumber().toString()));
        params.add(new BasicNameValuePair("date_of_birth",UserDAOHelper.dateToDbFormat(user.getDateOfBirth())));
        params.add(new BasicNameValuePair("fb_id", user.getFbID()));

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
    public User get(Long id) {
        User user = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", id.toString()));

        try {
            JSONObject result = new PostCall(params, this.getUrl, this.jsonParser).execute().get();
            if (result != null) {
                user = UserDAOHelper.extractUserFromJson(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

        @Override
    public List<User> getAll() {
        List<User> userList = null;
            List<NameValuePair> params = new ArrayList<>();
            try {
                JSONObject result = new PostCall(params, this.getAllUrl, this.jsonParser).execute().get();
                if(result != null){
                    userList = UserDAOHelper.extractMultipleUsersFromJson(result);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        return userList;
    }
}
