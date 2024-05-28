package com.example.jobfinder.data.local.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int userID;
    public String username;
    public String password;
}
