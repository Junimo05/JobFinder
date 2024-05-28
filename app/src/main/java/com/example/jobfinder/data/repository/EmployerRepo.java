package com.example.jobfinder.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.jobfinder.MyApplication;

import java.util.List;

import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.Employer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class EmployerRepo {
    private final String TAG = getClass().getSimpleName();

    public MutableLiveData<List<Employer>> requestAllEmployers(){
        final MutableLiveData<List<Employer>> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiService = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        apiService.getEmployers().enqueue(new Callback<List<Employer>>() {
            @Override
            public void onResponse(Call<List<Employer>> call, Response<List<Employer>> response) {
                Log.e(TAG, "getListEmployer response="+response );
                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(TAG, "requestListEmployer response.size="+response.body().size() );
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Employer>> call, Throwable t) {
                Log.e(TAG, "requestListEmployer onFailure" + call.toString());
            }

        });
        return mutableLiveData;
    }
}
