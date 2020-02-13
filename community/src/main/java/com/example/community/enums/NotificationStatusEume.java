package com.example.community.enums;

public enum  NotificationStatusEume {

    UNREAD(0),
    READ(1);


    private int status;

    public int getStatus(){
        return status;
    }


    NotificationStatusEume(int status) {
        this.status = status;
    }
}
