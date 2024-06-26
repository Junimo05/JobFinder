package com.example.jobfinder.data.model;

public class User {
    private String userID;
    private String username;
    private String password;
    private String imgurl;
    private String role;

    public User(String userID, String username, String password, String imgurl, String role) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.imgurl = imgurl;
        this.role = role;
    }

    public User(){

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
