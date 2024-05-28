package com.example.jobfinder.main_screen_fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfinder.PostDetailActivity;
import com.example.jobfinder.SearchActivity;
import com.example.jobfinder.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import com.example.jobfinder.adapter.PostAdapter;
import com.example.jobfinder.model.Post;

public class HomeFragment extends Fragment implements LifecycleObserver {
    private ArrayList<Post> arrayList = new ArrayList<>();
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter2;
    private RecyclerView recyclerView2;
    private FragmentHomeBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        return view;
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        preparePostData();

        return binding.getRoot();

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences prefs = getActivity().getSharedPreferences("my_fragment_prefs", Context.MODE_PRIVATE);
        int savedPosition = prefs.getInt("fragment_scroll_position", 0);
        Log.e("ViewCreated_position: ", String.valueOf(savedPosition));
            binding.FMScrollView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if(savedPosition > 0) {
                        binding.FMScrollView.scrollTo(0, savedPosition);
                    }
                }
            });

    }
    @Override
    public void onStart() {
        super.onStart();
        binding.txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Destroyed_position: ", String.valueOf(binding.FMScrollView.getScrollY()));
        SharedPreferences prefs = getActivity().getSharedPreferences("my_fragment_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("fragment_scroll_position", binding.FMScrollView.getScrollY());
        editor.apply();
    }

    public void showUndoPost(int position, Post post) {
        try {
            Snackbar mySnackbar = Snackbar.make(binding.TopLayout, "Click to undo Post.", Snackbar.LENGTH_SHORT);
            mySnackbar.setAction("Undo", v -> {
                arrayList.add(position, post);
                postAdapter.notifyItemInserted(position);
            });
            mySnackbar.show();
        } catch (Exception ex) {
            Log.e("ShowUndoPost", ex.getMessage());
        }
    }

    private void preparePostData() {
        arrayList = new ArrayList<>();
        Post post = new Post("Nhan vien giao hang", "25/5/2024", "Nhan vien giao hang ban thoi gian", "7 - 10m", "Ko yeu cau kinh nghiem");
        arrayList.add(post);
        post = new Post("Nhan vien tap vu", "23/4/2024", "Nhan vien tap vu toan thoi gian", "12 - 15m", "Ko yeu cau kinh nghiem");
        arrayList.add(post);
        post = new Post("Nhan vien kinh doanh/ Sales", "13/4/2024", "Nhan vien kinh doanh toan thoi gian", "15 - 20m", "2 years+");
        arrayList.add(post);
        post = new Post("Sat Thu", "16/9/2025", "Sieu sat thu", "1 gold coin", "not dead");
        arrayList.add(post);
        post = new Post("Grab", "11/11/2024", "Lai xe dich vu", "7 - 10m", "Lai xe tot, thong thao duong pho");
        arrayList.add(post);
        postAdapter = new PostAdapter(getContext(), arrayList, new PostAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity().getApplicationContext(), arrayList.get(position).getJobTitle(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemHold(int position) {
                Snackbar mySnackbar = Snackbar.make(binding.TopLayout, "Are you sure you want to delete this movie?", Snackbar.LENGTH_SHORT);
                mySnackbar.setAction("Confirm", v -> {
                    Post deletedPost = arrayList.remove(position);
                    postAdapter.notifyItemRemoved(position);
                    showUndoPost(position, deletedPost);
                });
                mySnackbar.show();
            }
        });


        recyclerView = binding.recyclerView;
        recyclerView.setAdapter(postAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        postAdapter2 = new PostAdapter(getContext(), arrayList, new PostAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(getActivity().getApplicationContext(), arrayList.get(position).getJobTitle(), Toast.LENGTH_SHORT).show();
//                Log.e("position_Top", String.valueOf(binding.FMScrollView.getScrollY()));
                Intent intent = new Intent(getContext(), PostDetailActivity.class);
//                intent.putExtra("Salary", arrayList.get(position).getSalaryRange());
//                intent.putExtra("Experience", arrayList.get(position).getRequirements());
//                intent.putExtra("Description", arrayList.get(position).getDescription());
                startActivity(intent);
            }

            @Override
            public void onItemHold(int position) {
                Snackbar mySnackbar = Snackbar.make(binding.TopLayout, "Are you sure you want to delete this movie?", Snackbar.LENGTH_SHORT);
                mySnackbar.setAction("Confirm", v -> {
                    Post deletedPost = arrayList.remove(position);
                    postAdapter.notifyItemRemoved(position);
                    showUndoPost(position, deletedPost);
                });
                mySnackbar.show();
            }
        });
        recyclerView2 = binding.recyclerView2;
        recyclerView2.setAdapter(postAdapter2);
        recyclerView2.addItemDecoration(new DividerItemDecoration(recyclerView2.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }
}
