package com.example.jobfinder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfinder.R;
import com.example.jobfinder.data.model.Notification;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private ArrayList<Notification> arrayList;
    private Context context;
    private MyClickListener myClickListener;
    public NotificationAdapter(Context context, ArrayList<Notification> arrayList, MyClickListener myClickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.myClickListener = myClickListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_notification_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notification notify = arrayList.get(position);
        holder.notifyTitle.setText(notify.getNotifyTitle());
        holder.notifyDate.setText(notify.getNotifyDate());
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
        TextView notifyTitle, notifyDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            notifyTitle = itemView.findViewById(R.id.notify_item_title);
            notifyDate = itemView.findViewById(R.id.notify_item_date);
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
