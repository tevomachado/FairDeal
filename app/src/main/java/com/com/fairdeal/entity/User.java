package com.com.fairdeal.entity;

import java.util.Date;

/**
 * Created by Carlos Eduardo on 16/11/2015.
 */
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String stagNumber;
    private Date dateOfBirth;
    private Long buildingId;
    private String phoneNumber;
    private String fbID;

    public User(){

    }

    public User(Long id, String firstName, String lastName, String stagNumber, Date dateOfBirth, Long buildingId, String phoneNumber, String fbID){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stagNumber = stagNumber;
        this.dateOfBirth = dateOfBirth;
        this.buildingId = buildingId;
        this.phoneNumber = phoneNumber;
        this.fbID = fbID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStagNumber() {
        return stagNumber;
    }

    public void setStagNumber(String stagNumber) {
        this.stagNumber = stagNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFbID() {
        return fbID;
    }

    public void setFbID(String fbID) {
        this.fbID = fbID;
    }
}
