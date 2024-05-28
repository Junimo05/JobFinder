package com.example.jobfinder.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.jobfinder.MyApplication;

import java.util.List;

import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.JobGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobGroupRepo {
private final String TAG = getClass().getSimpleName();

    public MutableLiveData<List<JobGroup>> requestAllJobGroups(){
        final MutableLiveData<List<JobGroup>> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiService = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        apiService.getJobGroups().enqueue(new Callback<List<JobGroup>>() {
            @Override
            public void onResponse(Call<List<JobGroup>> call, Response<List<JobGroup>> response) {
                Log.e(TAG, "getListJobGroup response="+response );
                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(TAG, "requestListJobGroup response.size="+response.body().size() );
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<JobGroup>> call, Throwable t) {
                Log.e(TAG, "requestListJobGroup onFailure" + call.toString());
            }

        });
        return mutableLiveData;
    }

}
