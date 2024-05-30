package com.example.jobfinder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfinder.R;
import com.example.jobfinder.data.model.JobGroup;

import java.util.ArrayList;

public class CategorySeeAllAdapter extends RecyclerView.Adapter<CategorySeeAllAdapter.MyViewHolder>{

    private final MyClickListener myClickListener;

    private ArrayList<JobGroup> jobGroups;

    public CategorySeeAllAdapter(ArrayList<JobGroup> jobGroups, MyClickListener myClickListener) {
        this.jobGroups = jobGroups;
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_item_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        JobGroup jobGroup = jobGroups.get(position);
        holder.JobGroupTitle.setText(jobGroup.getJobGroupTitle());
        holder.JobCount.setText(jobGroup.getJobCount() + " Jobs");
    }

    @Override
    public int getItemCount() {
        return jobGroups.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView JobGroupTitle, JobCount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            JobGroupTitle = itemView.findViewById(R.id.categoryName);
            JobCount = itemView.findViewById(R.id.categoryJobs);
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
