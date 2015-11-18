package com.com.fairdeal.entity;

import java.math.BigDecimal;

/**
 * Created by Carlos Eduardo on 18/11/2015.
 */
public class Announcement {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Long qualityId;
    private Long announcerId;
    private boolean negociationFlag;
    private boolean soldFlag;

    public Announcement(){

    }

    public Announcement(Long id, String title, String description, BigDecimal price, Long qualityId, Long announcerId, boolean negociationFlag, boolean soldFlag){
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.qualityId = qualityId;
        this.announcerId = announcerId;
        this.negociationFlag = negociationFlag;
        this.soldFlag = soldFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQualityId() {
        return qualityId;
    }

    public void setQualityId(Long qualityId) {
        this.qualityId = qualityId;
    }

    public Long getAnnouncerId() {
        return announcerId;
    }

    public void setAnnouncerId(Long announcerId) {
        this.announcerId = announcerId;
    }

    public boolean isNegociationFlag() {
        return negociationFlag;
    }

    public void setNegociationFlag(boolean negociationFlag) {
        this.negociationFlag = negociationFlag;
    }

    public boolean isSoldFlag() {
        return soldFlag;
    }

    public void setSoldFlag(boolean soldFlag) {
        this.soldFlag = soldFlag;
    }
}
