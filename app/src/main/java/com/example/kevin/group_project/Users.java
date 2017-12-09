package com.example.kevin.group_project;

import com.example.kevin.group_project.R;

import java.util.Date;

/**
 * Created by kgay on 11/9/17.
 */

public class Users {

    //Prop
    public String email;
    public String gender;
    public String fullname;
    public String city;
    public String education;
    public String dateofBirth;
    // public () profilePicture // need a data type to store picture


    public Users() {
    }

    public Users(String email, String gender, String fullname, String city, String education, String dateofBirth) {
        this.email = email;
        this.gender = gender;
        this.fullname = fullname;
        this.city = city;
        this.education = education;
        this.dateofBirth = dateofBirth;
    }

}
