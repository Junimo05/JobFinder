package com.example.jobfinder.data.model;

public class ChangePassword {
    private String username;

    private String newPassword;

    public ChangePassword(String username, String newPassword) {
        this.username = username;
        this.newPassword = newPassword;
    }
}
