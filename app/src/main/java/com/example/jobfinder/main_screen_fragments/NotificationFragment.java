package com.example.jobfinder.main_screen_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfinder.R;
import com.example.jobfinder.adapter.NotificationAdapter;
import com.example.jobfinder.data.model.Notification;
import com.example.jobfinder.databinding.FragmentNotificationBinding;
import com.example.jobfinder.model.Post;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class NotificationFragment extends Fragment implements LifecycleObserver {
    private ArrayList<Notification> arrayList = new ArrayList<>();
    private NotificationAdapter notificationAdapter;
    private RecyclerView recyclerView;
    private FragmentNotificationBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        prepareNotifyData();
        return binding.getRoot();
    }
    private void prepareNotifyData() {
        arrayList = new ArrayList<>();
        Notification notification = new Notification(1,"1", "1/1/2024");
        arrayList.add(notification);
        notification = new Notification(2, "2", "2/2/2024");
        arrayList.add(notification);
        notification = new Notification(3,"3", "3/3/2024");
        arrayList.add(notification);
        notification = new Notification(4, "4", "4/4/2024");
        arrayList.add(notification);
        notification = new Notification(5,"5", "5/5/2024");
        arrayList.add(notification);
        notification = new Notification(6, "6", "6/6/2024");
        arrayList.add(notification);
        notification = new Notification(7,"7", "7/7/2024");
        arrayList.add(notification);
        notification = new Notification(8, "8", "8/8/2024");
        arrayList.add(notification);
        notificationAdapter = new NotificationAdapter(getContext(), arrayList, new NotificationAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity().getApplicationContext(), arrayList.get(position).getNotifyTitle(), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onItemHold(int position) {
                Toast.makeText(getActivity().getApplicationContext(), "Item Hold", Toast.LENGTH_SHORT);
            }
        });
        recyclerView = binding.recyclerView;
        recyclerView.setAdapter(notificationAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }
}
