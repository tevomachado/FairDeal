package com.com.fairdeal.entity;

/**
 * Created by Carlos Eduardo on 18/11/2015.
 */
public class Email {
    private Long id;
    private Long userId;
    private String email;

    public Email(){}

    public Email(Long userId, String email){
        this.userId = userId;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
