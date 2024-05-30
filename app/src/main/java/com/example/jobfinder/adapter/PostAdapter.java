package com.example.jobfinder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfinder.R;
import com.example.jobfinder.model.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private ArrayList<Post> arrayList;
    private Context context;
    private MyClickListener myClickListener;
    public PostAdapter(Context context, ArrayList<Post> arrayList, MyClickListener myClickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.myClickListener = myClickListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post post = arrayList.get(position);
        holder.JobTitle.setText(post.getJobTitle());
        holder.PostDate.setText(post.getPostDate());
        holder.SalaryRange.setText(post.getSalaryRange());
//        Picasso.get()
//                .load(item.getImages().get(0))
//                .into(holder.image);
    }
    @Override
    public int getItemCount() {
        if(arrayList != null && arrayList.size() > 0) {
            return arrayList.size();
        }
        else
            return 0;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView JobTitle, PostDate, Description, SalaryRange, Requirements;
//        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            JobTitle = itemView.findViewById(R.id.post_Title);
            PostDate = itemView.findViewById(R.id.post_Date);
            SalaryRange = itemView.findViewById(R.id.post_SalaryHolder);
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
