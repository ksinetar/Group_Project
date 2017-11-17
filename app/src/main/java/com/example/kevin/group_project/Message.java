package com.example.kevin.group_project;

import com.example.kevin.group_project.R;

import java.util.Date;

/**
 * Created by kgay on 11/9/17.
 */

public class Message {

    //Prop
    public short timeStamp;
    public String messageSender;
    public String messageContent;

    public Message(){
    }

    public Message(short timeStamp, String messageSender, String messageContent){
        this.timeStamp = timeStamp;
        this.messageSender = messageSender;
        this.messageContent = messageContent;

    }

}

