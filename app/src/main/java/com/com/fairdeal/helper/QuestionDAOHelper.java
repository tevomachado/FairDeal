package com.com.fairdeal.helper;

import com.com.fairdeal.entity.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Carlos Eduardo on 18/11/2015.
 */
public class QuestionDAOHelper {

    public static Question extractQuestionFromJson(JSONObject jsonObject) throws JSONException, ParseException {
        JSONArray questionArray = jsonObject.getJSONArray("question");
        JSONObject questionObject = questionArray.getJSONObject(0);
        Question question = new Question();
        question.setId(questionObject.getLong("id"));
        question.setAnnouncementId(questionObject.getLong("announcement_id"));
        question.setUserId(questionObject.getLong("user_id"));
        question.setDate(DateHelper.stringToDate(questionObject.getString("date")));
        question.setQuestion(questionObject.getString("question"));
        question.setAnswer(questionObject.getString("answer"));
        return question;
    }

    public static List<Question> extractMultipleQuestionsFromJson(JSONObject jsonObject) throws JSONException, ParseException {
        JSONArray questionsArray = jsonObject.getJSONArray("questions");
        List<Question> questionList = new ArrayList<>();
        for(int i = 0; i < questionsArray.length(); i++){
            JSONObject questionObject = questionsArray.getJSONObject(i);
            Question question = new Question();
            question.setId(questionObject.getLong("id"));
            question.setAnnouncementId(questionObject.getLong("announcement_id"));
            question.setUserId(questionObject.getLong("user_id"));
            question.setDate(DateHelper.stringToDate(questionObject.getString("date")));
            question.setQuestion(questionObject.getString("question"));
            question.setAnswer(questionObject.getString("answer"));
            questionList.add(question);
        }
        return questionList;
    }
}
