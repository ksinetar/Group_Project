package com.example.kevin.group_project;

import com.example.kevin.group_project.R;

import java.util.Date;

/**
 * Created by kgay on 11/9/17.
 */

public class Group {

    //Prop
    private double memberCount;
    private double categoryRank;
    private Date creationDate;
    private String category;
    private String groupName;
    private String groupDescription;
    private Boolean privacy;
    // private () groupPicture // need a data type to store picture


    public Group(){
    }

    public Group(double memberCount, double categoryRank, Date creationDate, String category, String groupName, String groupDescription, Boolean privacy){
        this.memberCount = memberCount;
        this.categoryRank = categoryRank;
        this.creationDate = creationDate;
        this.category = category;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.privacy = privacy;


    }

}