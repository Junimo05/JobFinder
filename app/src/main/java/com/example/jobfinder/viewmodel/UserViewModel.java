package com.example.jobfinder.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.jobfinder.data.model.User;
import com.example.jobfinder.data.repository.UserRepo;

import java.util.List;

public class UserViewModel extends ViewModel {
    private UserRepo userRepo;
    private MutableLiveData<List<User>> mutableLiveData;

    public UserViewModel(){
        userRepo = new UserRepo();
    }

    public LiveData<List<User>> getUsersRepository(){
        try {
            if(mutableLiveData==null){
                mutableLiveData = userRepo.requestAllUsers();
            }
        } catch (Exception ex) {
            System.out.println("User viewmodel" + ex.getMessage());
        }
        return mutableLiveData;
    }



}
