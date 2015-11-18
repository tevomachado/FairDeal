package com.fairdeal.dao;

import com.com.fairdeal.entity.Question;
import com.com.fairdeal.helper.DateHelper;
import com.com.fairdeal.helper.JSONParser;
import com.com.fairdeal.helper.PostCall;
import com.com.fairdeal.helper.QuestionDAOHelper;

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
public class QuestionDAO implements InterfaceDAO<Question> {

    private String addUrl = "http://10.0.2.2/android_connect/question/create_question.php";
    private String removeUrl = "http://10.0.2.2/android_connect/question/delete_question.php";
    private String updateUrl = "http://10.0.2.2/android_connect/question/update_question.php";
    private String getUrl = "http://10.0.2.2/android_connect/question/get_question_details.php";
    private String getAllUrl = "http://10.0.2.2/android_connect/question/get_all_questions.php";
    private String getQuestionsByAnnouncementUrl = "http://10.0.2.2/android_connect/question/get_announcement_questions.php";
    private String getQuestionsByUserUrl = "http://10.0.2.2/android_connect/question/get_user_questions.php";

    private JSONParser jsonParser;

    public QuestionDAO(JSONParser jsonParser){
        this.jsonParser = jsonParser;
    }

    @Override
    public String add(Question question) {
        String id = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("announcement_id", question.getAnnouncementId().toString()));
        params.add(new BasicNameValuePair("user_id", question.getUserId().toString()));
        params.add(new BasicNameValuePair("question", question.getQuestion()));
        params.add(new BasicNameValuePair("answer", question.getAnswer()));
        params.add(new BasicNameValuePair("date", DateHelper.dateToDbFormat(question.getDate())));

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
    public boolean update(Question question) {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("announcement_id", question.getAnnouncementId().toString()));
        params.add(new BasicNameValuePair("user_id", question.getUserId().toString()));
        params.add(new BasicNameValuePair("question", question.getQuestion()));
        params.add(new BasicNameValuePair("answer", question.getAnswer()));
        params.add(new BasicNameValuePair("date", DateHelper.dateToDbFormat(question.getDate())));
        params.add(new BasicNameValuePair("id", question.getId().toString()));

        try {
            JSONObject result =  new PostCall(params, this.updateUrl, this.jsonParser).execute().get();
            System.out.println(result.toString());
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
    public Question get(Long id) {
        Question question = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", id.toString()));

        try {
            JSONObject result = new PostCall(params, this.getUrl, this.jsonParser).execute().get();
            if (result != null) {
                question = QuestionDAOHelper.extractQuestionFromJson(result);
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
        return question;
    }

    @Override
    public List<Question> getAll() {
        List<Question> questionList = null;
        List<NameValuePair> params = new ArrayList<>();
        try {
            JSONObject result = new PostCall(params, this.getAllUrl, this.jsonParser).execute().get();
            if(result != null){
                questionList = QuestionDAOHelper.extractMultipleQuestionsFromJson(result);
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

        return questionList;
    }

    public List<Question> getQuestionsByAnnouncementId(Long announcementId){
        List<Question> questionList = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("announcement_id", announcementId.toString()));
        try {
            JSONObject result = new PostCall(params, this.getQuestionsByAnnouncementUrl, this.jsonParser).execute().get();
            if(result != null){
                questionList = QuestionDAOHelper.extractMultipleQuestionsFromJson(result);
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
        return questionList;
    }

    public List<Question> getQuestionsByUserId(Long userId){
        List<Question> questionList = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user_id", userId.toString()));
        try {
            JSONObject result = new PostCall(params, this.getQuestionsByUserUrl, this.jsonParser).execute().get();
            if(result != null){
                questionList = QuestionDAOHelper.extractMultipleQuestionsFromJson(result);
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
        return questionList;
    }
}
