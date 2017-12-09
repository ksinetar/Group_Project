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
    public String university;
    public String dateofBirth;
    // public () profilePicture // need a data type to store picture


    public Users(){
    }

    public Users(String email, String gender, String fullname, String city, String education, String dateofBirth){
        this.email = email;
        this.gender = gender;
        this.fullname = fullname;
        this.city = city;
        this.university = education;
        this.dateofBirth = dateofBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }
}
