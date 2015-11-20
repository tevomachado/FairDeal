package com.com.fairdeal.entity;

/**
 * Created by Carlos Eduardo on 19/11/2015.
 */
public enum Rating {
    BAD(1L),
    MEDIUM(2L),
    GOOD(3L),
    EXCELENT(4L),
    AWESOME(5L);

    private Long value;

    Rating(Long value) {
        this.value = value;
    }

    public Long getValue(){
        return this.value;
    }
}
