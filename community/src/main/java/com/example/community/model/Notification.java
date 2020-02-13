package com.example.community.model;

import lombok.Data;

@Data
public class Notification
{
    Integer id;
    Integer notifier;
    Integer receiver;
    Integer outer_id;
    Integer type;
    Long gmt_create;
    Integer status;




}
