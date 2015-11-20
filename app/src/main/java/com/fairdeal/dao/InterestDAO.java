package com.fairdeal.dao;

import com.com.fairdeal.entity.Interest;
import com.com.fairdeal.helper.InterestDAOHelper;
import com.com.fairdeal.helper.JSONParser;
import com.com.fairdeal.helper.PostCall;

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
public class InterestDAO implements InterfaceDAO<Interest> {

    private String addUrl = "http://10.0.2.2/android_connect/interest/create_interest.php";
    private String removeUrl = "http://10.0.2.2/android_connect/interest/delete_interest.php";
    private String updateUrl = "http://10.0.2.2/android_connect/interest/update_interest.php";
    private String getUrl = "http://10.0.2.2/android_connect/interest/get_interest_details.php";
    private String getAllUrl = "http://10.0.2.2/android_connect/interest/get_all_interests.php";
    private String getInterestbyAnnouncementIdUrl = "http://10.0.2.2/android_connect/interest/get_interest_by_announcement.php";
    private String getInterestbyUserIdUrl = "http://10.0.2.2/android_connect/interest/get_interest_by_user.php";

    private JSONParser jsonParser;

    public InterestDAO(JSONParser jsonParser){
        this.jsonParser = jsonParser;
    }

    @Override
    public String add(Interest interest) {
        String id = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user_id", interest.getUserId().toString()));
        params.add(new BasicNameValuePair("announcement_id", interest.getAnnouncementId().toString()));

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
    public boolean update(Interest interest) {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", interest.getId().toString()));
        params.add(new BasicNameValuePair("announcement_id", interest.getAnnouncementId().toString()));
        params.add(new BasicNameValuePair("user_id", interest.getUserId().toString()));

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
    public Interest get(Long id) {
        Interest interest = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", id.toString()));

        try {
            JSONObject result = new PostCall(params, this.getUrl, this.jsonParser).execute().get();
            if (result != null) {
                interest = InterestDAOHelper.extractInterestFromJson(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return interest;
    }

    @Override
    public List<Interest> getAll() {
        List<Interest> interestList = null;
        List<NameValuePair> params = new ArrayList<>();
        try {
            JSONObject result = new PostCall(params, this.getAllUrl, this.jsonParser).execute().get();
            if(result != null){
                interestList = InterestDAOHelper.extractMultipleInterestsFromJson(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return interestList;
    }

    public List<Interest> getInterestByAnnouncementId(Long announcementId){
        List<Interest> interestList = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("announcement_id", announcementId.toString()));

        try {
            JSONObject result = new PostCall(params, this.getInterestbyAnnouncementIdUrl, this.jsonParser).execute().get();
            if (result != null) {
                interestList = InterestDAOHelper.extractMultipleInterestsFromJson(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return interestList;
    }

    public List<Interest> getInterestByUserId(Long userId){
        List<Interest> interestList = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user_id", userId.toString()));

        try {
            JSONObject result = new PostCall(params, this.getInterestbyUserIdUrl, this.jsonParser).execute().get();
            if (result != null) {
                interestList = InterestDAOHelper.extractMultipleInterestsFromJson(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return interestList;
    }
}
