package com.example.jobfinder.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.jobfinder.data.local.Entity.User;
import com.example.jobfinder.data.local.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
