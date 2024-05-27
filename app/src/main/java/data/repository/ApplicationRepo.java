package data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.jobfinder.MyApplication;

import java.util.List;

import data.api.ApiInterface;
import data.model.Application;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ApplicationRepo {
    private static final String TAG = "ApplicationRepo";
    private ApiInterface apiInterface;

    public ApplicationRepo() {
        apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);
    }

    public MutableLiveData<List<Application>> getApplications() {
        MutableLiveData<List<Application>> applications = new MutableLiveData<>();
        apiInterface.getApplications().enqueue(new Callback<List<Application>>() {
            @Override
            public void onResponse(Call<List<Application>> call, Response<List<Application>> response) {
                if (response.isSuccessful()) {
                    applications.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Application>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return applications;
    }

}
