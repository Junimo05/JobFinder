package com.example.jobfinder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfinder.R;
import com.example.jobfinder.data.model.Job;

import java.util.ArrayList;

public class CategoryListJobAdapter extends RecyclerView.Adapter<CategoryListJobAdapter.MyViewHolder>{

    private final MyClickListener myClickListener;
    private ArrayList<Job> jobs;

    public CategoryListJobAdapter(ArrayList<Job> jobs, MyClickListener myClickListener) {
        this.jobs = jobs;
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_job_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListJobAdapter.MyViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.JobTitle.setText(job.getJobTitle());
        holder.Location.setText(job.getLocation());
    }

    @Override
    public int getItemCount() {

        if(jobs != null && jobs.size() > 0) {
            return jobs.size();
        }
        else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView JobTitle, Location;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            JobTitle = itemView.findViewById(R.id.tvJobTitle);
            Location = itemView.findViewById(R.id.tvJobLocation);
            itemView.setOnClickListener(v -> {
                myClickListener.onItemClick(getAdapterPosition());
            });
            itemView.setOnLongClickListener(v -> {
                myClickListener.onItemHold(getAdapterPosition());
                return true;
            });
        }

    }

    public interface MyClickListener {
        void onItemClick(int position);
        void onItemHold(int position);
    }
}
