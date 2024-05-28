package com.example.jobfinder.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.jobfinder.MyApplication;
import com.example.jobfinder.data.api.ApiInterface;

import java.util.List;

import com.example.jobfinder.data.model.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepo {
    private final String TAG = getClass().getSimpleName();

    public MutableLiveData<List<Employee>> requestAllEmployees(){
        final MutableLiveData<List<Employee>> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiService = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        apiService.getEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                Log.e(TAG, "getListEmployee response="+response );
                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(TAG, "requestListEmployee response.size="+response.body().size() );
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.e(TAG, "requestListEmployee onFailure" + call.toString());
            }

        });
        return mutableLiveData;
    }
}
