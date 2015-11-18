package com.com.fairdeal.helper;


import com.com.fairdeal.entity.Announcement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Eduardo on 18/11/2015.
 */
public class AnnouncementDAOHelper {

    private static String pattern = "#,##0.0#";

    public static String booleanToString(boolean flag){
        if(flag){
            return "1";
        }
        return "0";
    }

    public static boolean stringToBoolean(String flag){
        return flag.equals("1");
    }

    public static Announcement extractAnnouncementFromJson(JSONObject jsonObject) throws JSONException, ParseException {
        JSONArray announcementArray = jsonObject.getJSONArray("announcement");
        JSONObject announcementObject = announcementArray.getJSONObject(0);
        Announcement announcement = new Announcement();
        announcement.setId(announcementObject.getLong("id"));
        announcement.setTitle(announcementObject.getString("title"));
        announcement.setDescription(announcementObject.getString("description"));
        announcement.setPrice(new BigDecimal(announcementObject.getString("price")));
        announcement.setQualityId(announcementObject.getLong("quality_id"));
        announcement.setAnnouncerId(announcementObject.getLong("announcer_id"));
        announcement.setNegociationFlag(stringToBoolean(announcementObject.getString("negotiation_flag")));
        announcement.setSoldFlag(stringToBoolean(announcementObject.getString("sold_flag")));
        System.out.println(announcement.getTitle());
        return announcement;
    }

    public static BigDecimal stringToBigDecimal(String string) throws ParseException {
        return (BigDecimal) new DecimalFormat(pattern).parse(string);
    }

    public static List<Announcement> extractMultipleAnnouncementsFromJson(JSONObject jsonObject) throws JSONException, ParseException {
        JSONArray announcementsArray = jsonObject.getJSONArray("announcements");
        List<Announcement> announcementsList = new ArrayList<>();
        for(int i = 0; i < announcementsArray.length(); i++) {
            JSONObject announcementObject = announcementsArray.getJSONObject(i);
            Announcement announcement = new Announcement();
            announcement.setId(announcementObject.getLong("id"));
            announcement.setTitle(announcementObject.getString("title"));
            announcement.setDescription(announcementObject.getString("description"));
            announcement.setPrice(new BigDecimal(announcementObject.getString("price")));
            announcement.setQualityId(announcementObject.getLong("quality_id"));
            announcement.setAnnouncerId(announcementObject.getLong("announcer_id"));
            announcement.setNegociationFlag(stringToBoolean(announcementObject.getString("negotiation_flag")));
            announcement.setSoldFlag(stringToBoolean(announcementObject.getString("sold_flag")));
            announcementsList.add(announcement);
        }
        return announcementsList;
    }


}
