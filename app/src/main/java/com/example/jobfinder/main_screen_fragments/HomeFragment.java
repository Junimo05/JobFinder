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

import com.example.jobfinder.MyApplication;
import com.example.jobfinder.PostDetailActivity;
import com.example.jobfinder.SearchActivity;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.Job;

import com.example.jobfinder.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.jobfinder.adapter.PostAdapter;
import com.example.jobfinder.model.Post;

import retrofit2.Call;

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
        binding.txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("txtSearchClicked:", "true");
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
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

        //getData from API
        ApiInterface apiService = MyApplication.getRetrofitInstance().create(ApiInterface.class);
        try {
            Call<List<Job>> call = apiService.getJobs();
            call.enqueue(new retrofit2.Callback<List<Job>>() {
                @Override
                public void onResponse(Call<List<Job>> call, retrofit2.Response<List<Job>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Job> jobs = response.body();
//                        Log.e("preparePostData", "response.size=" + jobs.size());
//                        Log.e("preparePostData", "response=" + jobs.get(0).getJobTitle());
                        for (Job job : jobs) {
                            try {
                                String isoDate = job.getPostDate();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                                Date date = simpleDateFormat.parse(isoDate);
                                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                                String postDate = simpleDateFormat2.format(date);
                                job.setPostDate(postDate);  //set postDate to display in the app

                                String id = String.valueOf(job.getJobID());

                                Post post = new Post(id ,job.getJobTitle(), job.getPostDate(), job.getDescription(), job.getSalaryRange(), job.getRequirements());
                                arrayList.add(post);
                            } catch (Exception ex) {
                                Log.e("ErrorAPI JobList", ex.getMessage());
                            }
                        }
                        postAdapter.notifyDataSetChanged();
                        postAdapter2.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<Job>> call, Throwable t) {
                    Log.e("preparePostData", t.getMessage());
                }
            });
        } catch (Exception ex) {
            Log.e("preparePostData", ex.getMessage());
        }

        postAdapter = new PostAdapter(getContext(), arrayList, new PostAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity().getApplicationContext(), arrayList.get(position).getJobTitle(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemHold(int position) {
                Snackbar mySnackbar = Snackbar.make(binding.TopLayout, "", Snackbar.LENGTH_SHORT);
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
                Intent intent = new Intent(getContext(), PostDetailActivity.class);
                intent.putExtra("jobID", arrayList.get(position).getJobID().toString());
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
