package com.example.project.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {

    @SerializedName("users")
    public ArrayList<User> users;

    static public class User implements Serializable{
        @SerializedName("name")
        public String name;
        @SerializedName("profession")
        public String profession;
        @SerializedName("image")
        public String image;
        @SerializedName("active")
        public boolean active;
    }
}
