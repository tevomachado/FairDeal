package com.fairdeal.dao;

import com.com.fairdeal.entity.Announcement;
import com.com.fairdeal.helper.AnnouncementDAOHelper;
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
 * Created by Carlos Eduardo on 18/11/2015.
 */
public class AnnouncementDAO implements InterfaceDAO<Announcement> {

    private String addUrl = "http://10.0.2.2/android_connect/announcement/create_announcement.php";
    private String removeUrl = "http://10.0.2.2/android_connect/announcement/delete_announcement.php";
    private String updateUrl = "http://10.0.2.2/android_connect/announcement/update_announcement.php";
    private String getUrl = "http://10.0.2.2/android_connect/announcement/get_announcement_details.php";
    private String getAllUrl = "http://10.0.2.2/android_connect/announcement/get_all_announcements.php";
    private String getUserAnnouncementsUrl = "http://10.0.2.2/android_connect/announcement/get_user_announcements.php";
    private String getAnnouncementsByTitleUrl = "http://10.0.2.2/android_connect/announcement/get_announcements_by_title.php";

    private JSONParser jsonParser;

    public AnnouncementDAO(JSONParser jsonParser){
        this.jsonParser = jsonParser;
    }

    @Override
    public String add(Announcement announcement) {
        String id = null;

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("title", announcement.getTitle()));
        params.add(new BasicNameValuePair("description", announcement.getDescription()));
        params.add(new BasicNameValuePair("price", announcement.getPrice().toString()));
        params.add(new BasicNameValuePair("quality_id", announcement.getQualityId().toString()));
        params.add(new BasicNameValuePair("announcer_id", announcement.getAnnouncerId().toString()));
        params.add(new BasicNameValuePair("negotiation_flag", AnnouncementDAOHelper.booleanToString(announcement.isNegociationFlag())));
        params.add(new BasicNameValuePair("sold_flag", AnnouncementDAOHelper.booleanToString(announcement.isSoldFlag())));

        try{
            JSONObject result = new PostCall(params, this.addUrl, this.jsonParser).execute().get();
            if(result != null){
                id = result.getString("id");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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
    public boolean update(Announcement announcement) {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("title", announcement.getTitle()));
        params.add(new BasicNameValuePair("description", announcement.getDescription()));
        params.add(new BasicNameValuePair("price", announcement.getPrice().toString()));
        params.add(new BasicNameValuePair("quality_id", announcement.getQualityId().toString()));
        params.add(new BasicNameValuePair("announcer_id", announcement.getAnnouncerId().toString()));
        params.add(new BasicNameValuePair("negotiation_flag", AnnouncementDAOHelper.booleanToString(announcement.isNegociationFlag())));
        params.add(new BasicNameValuePair("sold_flag", AnnouncementDAOHelper.booleanToString(announcement.isSoldFlag())));
        params.add(new BasicNameValuePair("id", announcement.getId().toString()));
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
    public Announcement get(Long id) {
        Announcement announcement = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", id.toString()));

        try {
            JSONObject result = new PostCall(params, this.getUrl, this.jsonParser).execute().get();
            if (result != null) {
                announcement = AnnouncementDAOHelper.extractAnnouncementFromJson(result);
                System.out.println("retornou announcement");
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
        return announcement;
    }

    @Override
    public List<Announcement> getAll() {
        List<Announcement> announcementList = null;
        List<NameValuePair> params = new ArrayList<>();
        try {
            JSONObject result = new PostCall(params, this.getAllUrl, this.jsonParser).execute().get();
            if(result != null){
                announcementList = AnnouncementDAOHelper.extractMultipleAnnouncementsFromJson(result);
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

        return announcementList;
    }

    public List<Announcement> getAnnouncementsByUserId(String userId){
        List<Announcement> announcementList = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("announcer_id", userId));
        try {
            JSONObject result = new PostCall(params, this.getUserAnnouncementsUrl, this.jsonParser).execute().get();
            if(result != null){
                announcementList = AnnouncementDAOHelper.extractMultipleAnnouncementsFromJson(result);
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
        return announcementList;
    }

    public List<Announcement> getAnnouncementsByTitle(String title){
        List<Announcement> announcementList = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("title", title));
        try {
            JSONObject result = new PostCall(params, this.getAnnouncementsByTitleUrl, this.jsonParser).execute().get();
            if(result != null){
                announcementList = AnnouncementDAOHelper.extractMultipleAnnouncementsFromJson(result);
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
        return announcementList;
    }
}
