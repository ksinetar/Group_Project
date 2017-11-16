import com.example.kevin.group_project.R;

import java.util.Date;

/**
 * Created by kgay on 11/9/17.
 */

public class Users {

    //Prop
    public String email;
    public String gender;
    public String name;
    public String city;
    public String education;
    public Date dateofBirth;
    // public () profilePicture // need a data type to store picture


    public Users(){
    }

    public Users(String email, String gender, String name, String city, String education, Date dateofBirth){
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.city = city;
        this.education = education;
        this.dateofBirth = dateofBirth;


    }

}
