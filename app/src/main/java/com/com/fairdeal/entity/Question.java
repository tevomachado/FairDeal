package com.com.fairdeal.entity;

import java.util.Date;

/**
 * Created by Carlos Eduardo on 18/11/2015.
 */
public class Question {

    private Long id;
    private Long announcementId;
    private Long userId;
    private Date date;
    private String question;
    private String answer;

    public Question(){

    }

    public Question(Long id, Long announcementId, Long userId, Date date, String question, String answer){
        this.id = id;
        this.announcementId = announcementId;
        this.userId = userId;
        this.date = date;
        this.question = question;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
