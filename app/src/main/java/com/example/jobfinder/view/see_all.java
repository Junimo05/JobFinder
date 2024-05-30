package com.example.jobfinder.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfinder.MainActivity;
import com.example.jobfinder.MyApplication;
import com.example.jobfinder.PostDetailActivity;
import com.example.jobfinder.R;
import com.example.jobfinder.adapter.CategorySeeAllAdapter;
import com.example.jobfinder.adapter.PopularSeeAllAdapter;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.Job;
import com.example.jobfinder.data.model.JobGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class see_all extends AppCompatActivity {

    private ApiInterface apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);

    private ArrayList<JobGroup> jobGroups = new ArrayList<>();
    private CategorySeeAllAdapter categorySeeAllAdapter;
    private ArrayList<Job> jobs = new ArrayList<>();

    private PopularSeeAllAdapter popularSeeAllAdapter;

    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);
        //back button
        ImageView back = findViewById(R.id.backButton);

        //rv
        rv = findViewById(R.id.rv_ListItem);

        String see_all = getIntent().getStringExtra("see_all");

        if (Objects.equals(see_all, "job group")) {
            prepareCategory();
        } else if (Objects.equals(see_all, "job")) {
            prepareJobs();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        UIDisplay(getIntent().getStringExtra("see_all"));
    }

    public void UIDisplay(String see_all){
        if (Objects.equals(see_all, "job group")) {
            TextView title = findViewById(R.id.list_display_title);
            title.setText("Category");
            categorySeeAllAdapter = new CategorySeeAllAdapter(jobGroups, new CategorySeeAllAdapter.MyClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(see_all.this, CategoryListJob.class);
                    intent.putExtra("titleGroup", jobGroups.get(position).getJobGroupTitle());
                    startActivity(intent);
                }

                @Override
                public void onItemHold(int position) {
                    //do nothing
                }
            });

            rv.setAdapter(categorySeeAllAdapter);
            rv.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (Objects.equals(see_all, "job")) {
            TextView title = findViewById(R.id.list_display_title);
            title.setText("Job");
            popularSeeAllAdapter = new PopularSeeAllAdapter(jobs, new PopularSeeAllAdapter.MyClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(see_all.this, PostDetailActivity.class);
                    intent.putExtra("jobID", String.valueOf(jobs.get(position).getJobID()));
                    startActivity(intent);
                }

                @Override
                public void onItemHold(int position) {
                    //do nothing
                }
            });

            rv.setAdapter(popularSeeAllAdapter);
            rv.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public void prepareCategory() {
        Call<List<JobGroup>> call = apiInterface.getJobGroups();

        call.enqueue(new Callback<List<JobGroup>>() {
            @Override
            public void onResponse(Call<List<JobGroup>> call, Response<List<JobGroup>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<JobGroup> res = response.body();
                    if(jobGroups != null) jobGroups.clear();
                    jobGroups.addAll(res);
                    UIDisplay(getIntent().getStringExtra("see_all"));
                }
            }

            @Override
            public void onFailure (Call < List < JobGroup >> call, Throwable t){
                t.printStackTrace();
            }
        });
    }
    public void prepareJobs(){
        Call<List<Job>> call = apiInterface.getJobs();
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Job> res = response.body();
                    if(jobs != null) jobs.clear();
                    jobs.addAll(res);
                    UIDisplay(getIntent().getStringExtra("see_all"));
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}