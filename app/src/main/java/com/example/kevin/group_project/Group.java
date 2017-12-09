package com.example.kevin.group_project;

import com.example.kevin.group_project.R;

import java.util.Date;

/**
 * Created by kgay on 11/9/17.
 */

public class Group {

    //Prop
   // private double memberCount;
   // private String category;
    private String groupName;
    private String groupDescription;
    private String groupCategory;

    // private () groupPicture // need a data type to store picture


    public Group(){
    }

    public Group(String groupName, String groupDescription, String groupCategory) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.groupCategory = groupCategory;

    }

    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String groupName) {this.groupName = groupName; }

    public String getGroupDescription(){
        return  this.groupDescription;
    }

    public void setGroupDescription(String groupDescription) {this.groupDescription = groupDescription; }

    public String getGroupCategory() {
        return this.groupCategory;
    }

    public void setGroupCategory(String groupCategory) {this.groupCategory = groupCategory; }


}