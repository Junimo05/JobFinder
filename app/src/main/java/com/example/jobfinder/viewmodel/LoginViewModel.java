package com.example.jobfinder.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jobfinder.MyApplication;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.LoginUser;
import com.example.jobfinder.data.model.User;
import com.example.jobfinder.utils.LoginStatus;

import retrofit2.Call;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<Integer> getLoginStatus() {
        return loginstatus;
    }

    private MutableLiveData<Integer> loginstatus = new MutableLiveData<>();
    private LoginUser loginUser = new LoginUser();

    private User user;

    public LoginViewModel() {
    }

    public LoginViewModel(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public void onclickLogin(){
        ApiInterface apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);
        try {
            if (validateData()) {
                Call<User> call = apiInterface.Login(loginUser);
                call.enqueue(new retrofit2.Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            User user = response.body();
                            if (user.getUserID() != null && user.getUsername() != null && user.getRole() != null) {
                                loginstatus.setValue(LoginStatus.loginSuccess);
                                user = response.body();
                            } else {
                                loginstatus.setValue(LoginStatus.loginFails);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        loginstatus.setValue(LoginStatus.loginFails);
                    }
                });
            }
        } catch (Exception ex) {
            System.out.println("Login viewmodel" + ex.getMessage());
        }
    }

    private boolean validateData() {
        try {
            if (loginUser.getUsername().isEmpty()) {
                loginstatus.setValue(LoginStatus.emptyUsername);
                return false;
            } else if (loginUser.getPassword().isEmpty()) {
                loginstatus.setValue(LoginStatus.emptyPassWord);
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Validate viewmodel" + ex.getMessage());
        }
        return true;
    }

    public User getUser() {
        return user;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
