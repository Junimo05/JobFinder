package com.example.jobfinder.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfinder.PostDetailActivity;
import com.example.jobfinder.R;
import com.example.jobfinder.adapter.CategoryListJobAdapter;
import com.example.jobfinder.data.model.Job;
import com.example.jobfinder.data.model.JobGroup;
import com.example.jobfinder.databinding.ActivityCategoryListJobBinding;
import com.example.jobfinder.viewmodel.JobViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryListJob extends AppCompatActivity {
    private JobViewModel jobViewModel;

    private ArrayList<Job> jobs;

    private ActivityCategoryListJobBinding binding;
    private RecyclerView rvJobList;
    public CategoryListJobAdapter ListJobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_list_job);
        jobs = new ArrayList<>();
        TextView tvTitle = findViewById(R.id.list_display_title);
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Log.e("CategoryListJob", "titleGroup: " + getIntent().getStringExtra("titleGroup"));
        tvTitle.setText(getIntent().getStringExtra("titleGroup"));

        ImageView back= findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        prepareData();
    }


    public void prepareData(){
        String title = getIntent().getStringExtra("titleGroup");
        jobViewModel = new ViewModelProvider(this).get(JobViewModel.class);
        jobViewModel.getJobByJobGroup(title);
        jobViewModel.getJobListByGroup().observe(this, new Observer<List<Job>>(){

            @Override
            public void onChanged(List<Job> jobList) {
                // Update the data for the adapter and notify it to refresh the RecyclerView
                if (jobs != null) jobs.clear();
                jobs.addAll(jobList);
                ListJobAdapter.notifyDataSetChanged();
            }
        });

        ListJobAdapter = new CategoryListJobAdapter(jobs, new CategoryListJobAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e("Data: ", String.valueOf(jobs.get(position).getJobID()));
                Intent intent = new Intent(CategoryListJob.this, PostDetailActivity.class);

                intent.putExtra("jobID", String.valueOf(jobs.get(position).getJobID()));

                startActivity(intent);

            }
            @Override
            public void onItemHold(int position) {

            }
        });

        rvJobList = findViewById(R.id.rv_ListItem);
        rvJobList.setLayoutManager(new LinearLayoutManager(this));
        rvJobList.setAdapter(ListJobAdapter);
    }

}