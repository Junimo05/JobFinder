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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListJobAdapter.MyViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.JobTitle.setText(job.getJobTitle());
        holder.SalaryRange.setText(job.getSalaryRange());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView JobTitle, companyName, SalaryRange;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            JobTitle = itemView.findViewById(R.id.tvTitleJob);
            SalaryRange = itemView.findViewById(R.id.tvJobSalary);
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
