package com.fairdeal.dao;

import com.com.fairdeal.entity.Email;
import com.com.fairdeal.helper.EmailDAOHelper;
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
public class EmailDAO implements InterfaceDAO<Email> {

    private String addUrl = "http://10.0.2.2/android_connect/email/create_email.php";
    private String removeUrl = "http://10.0.2.2/android_connect/email/delete_email.php";
    private String updateUrl = "http://10.0.2.2/android_connect/email/update_email.php";
    private String getUrl = "http://10.0.2.2/android_connect/email/get_email_details.php";
    private String getAllUrl = "http://10.0.2.2/android_connect/email/get_all_emails.php";
    private String getUserEmailsUrl = "http://10.0.2.2/android_connect/email/get_emails_user.php";
    private String checkExistingEmailUrl = "http://10.0.2.2/android_connect/email/check_existing_email.php";

    private JSONParser jsonParser;

    public EmailDAO(JSONParser jsonParser){
        this.jsonParser = jsonParser;
    }

    @Override
    public String add(Email email) {
        String id = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("email", email.getEmail()));
        params.add(new BasicNameValuePair("user_id", email.getUserId().toString()));

        try {
            JSONObject result = new PostCall(params, this.addUrl, this.jsonParser).execute().get();
            if(result != null) {
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
    public boolean update(Email email) {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", email.getId().toString()));
        params.add(new BasicNameValuePair("user_id", email.getUserId().toString()));
        params.add(new BasicNameValuePair("email", email.getEmail()));

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
    public Email get(Long id) {
        Email email = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", id.toString()));

        try {
            JSONObject result = new PostCall(params, this.getUrl, this.jsonParser).execute().get();
            if (result != null) {
                email = EmailDAOHelper.extractEmailFromJson(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return email;
    }

    @Override
    public List<Email> getAll() {
        List<Email> emailList = null;
        List<NameValuePair> params = new ArrayList<>();
        try {
            JSONObject result = new PostCall(params, this.getAllUrl, this.jsonParser).execute().get();
            if(result != null){
                emailList = EmailDAOHelper.extractMultipleEmailsFromJson(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return emailList;
    }

    public List<Email> getEmailsByUserId(Long userId){
        List<Email> emailList = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user_id", userId.toString()));
        try {
            JSONObject result = new PostCall(params, this.getUserEmailsUrl, this.jsonParser).execute().get();
            if(result != null){
                emailList = EmailDAOHelper.extractMultipleEmailsFromJson(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return emailList;
    }

    public boolean checkExistingEmail(String emailAddress){
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("email", emailAddress));

        try {
            JSONObject result = new PostCall(params, this.checkExistingEmailUrl, this.jsonParser).execute().get();
            if (result != null) {
               return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
