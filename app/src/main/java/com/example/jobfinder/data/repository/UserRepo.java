package com.example.jobfinder.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.example.jobfinder.MyApplication;

import java.util.List;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class UserRepo {
    private final String TAG = getClass().getSimpleName();

    public MutableLiveData<List<User>> requestAllUsers(){
        final MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiService = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.e(TAG, "getListUser response="+response );
                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(TAG, "requestListUser response.size="+response.body().size() );
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(TAG, "requestListUser onFailure" + call.toString());
            }

        });
        return mutableLiveData;
    }
}
