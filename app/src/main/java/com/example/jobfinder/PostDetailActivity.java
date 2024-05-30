package com.example.jobfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.Job;
import com.example.jobfinder.databinding.ActivityPostDetailBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailActivity extends AppCompatActivity {
    private ActivityPostDetailBinding binding;
    String jobName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jobID = getIntent().getStringExtra("jobID");
        Log.e("JobCheck", jobID);
        ApiInterface apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        binding = ActivityPostDetailBinding.inflate(getLayoutInflater());


        try {
            Call<Job> call = apiInterface.getJobById(jobID);
            call.enqueue(new Callback<Job>() {
                @Override
                public void onResponse(Call<Job> call, Response<Job> response) {
                    if (response.isSuccessful() && response.body() != null){
                        Job job = response.body();
                        binding.PDDescription.setText(job.getDescription());
                        binding.PDLocation.setText(job.getLocation());
                        binding.PDSalary.setText(job.getSalaryRange());
                        binding.PDExperience.setText(job.getRequirements());
                        jobName = job.getJobTitle();
                    }
                }
                @Override
                public void onFailure(Call<Job> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }



        setContentView(binding.getRoot());
        binding.PDBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.PDApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CVActivity.class);
                Log.e("jobName:", jobName);
                intent.putExtra("jobName", jobName);
                intent.putExtra("jobID", jobID);
                startActivity(intent);
            }
        });
    }
}
