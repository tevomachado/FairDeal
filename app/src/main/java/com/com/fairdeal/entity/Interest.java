package com.com.fairdeal.entity;

/**
 * Created by Carlos Eduardo on 19/11/2015.
 */
public class Interest {

    private Long id;
    private Long announcementId;
    private Long userId;

    public Interest(){}

    public Interest(Long id, Long announcementId, Long userId){
        this.id = id;
        this.announcementId = announcementId;
        this.userId = userId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
