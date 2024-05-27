package viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import data.model.User;
import data.repository.UserRepo;

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
