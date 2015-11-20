package com.com.fairdeal.entity;

/**
 * Created by Carlos Eduardo on 19/11/2015.
 */
public class UserRating {

    private Long id;
    private Rating rating;
    private Long userId;

    public UserRating(){}

    public UserRating(Long id, Rating rating, Long userId){
        this.id = id;
        this.rating = rating;
        this.userId = userId;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
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
