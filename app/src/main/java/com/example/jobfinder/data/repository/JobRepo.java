package com.example.jobfinder.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.jobfinder.MyApplication;

import java.util.List;

import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.Job;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobRepo {
    private final String TAG = getClass().getSimpleName();

    public MutableLiveData<List<Job>> requestAllJobs(){
        final MutableLiveData<List<Job>> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiService = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        apiService.getJobs().enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                Log.e(TAG, "getListJob response="+response );
                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(TAG, "requestListJob response.size="+response.body().size() );
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e(TAG, "requestListJob onFailure" + call.toString());
            }

        });
        return mutableLiveData;
    }
    public MutableLiveData<List<Job>> requestJobByJobGroup(String jobGroup){
        final MutableLiveData<List<Job>> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiService = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        apiService.getJobByJobGroup(jobGroup).enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(TAG, "requestJobByGroup response="+response.body());
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e(TAG, "requestJobByID onFailure" + call.toString());
            }
        });
        return mutableLiveData;
    }

}