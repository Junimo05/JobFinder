package com.example.jobfinder;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfinder.adapter.PostAdapter;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.Job;
import com.example.jobfinder.databinding.ActivitySearchBinding;
import com.example.jobfinder.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private ActivitySearchBinding binding;
    private ArrayList<Post> arrayList = new ArrayList<>();
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    private ApiInterface apiService = MyApplication.getRetrofitInstance().create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        postAdapter = new PostAdapter(getApplicationContext(), arrayList, new PostAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
                intent.putExtra("jobID", arrayList.get(position).getJobID());
                startActivity(intent);
            }

            @Override
            public void onItemHold(int position) {

            }
        });
        recyclerView = binding.ListSearchItems;
        recyclerView.setAdapter(postAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.SearchEditText.setOnEditorActionListener(this);
        binding.SearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.SearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 0) {
                    binding.SearchClearText.setVisibility(View.VISIBLE);
                }
                else {
                    binding.SearchClearText.setVisibility(View.INVISIBLE);
                }

            }
        });


        binding.SearchClearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.SearchEditText.setText("");
            }
        });
        setContentView(binding.getRoot());
    }
    @Override
    public boolean onEditorAction(TextView v, int actionID, KeyEvent event) {
        if(actionID == EditorInfo.IME_ACTION_DONE) {
            String text = binding.SearchEditText.getText().toString().trim();

            prepareSearchData(text);
            return true;
        }
        return false;
    };
    private void prepareSearchData(String title) {
        try {
            Call<List<Job>> call = apiService.getJobByTitle(title);
            call.enqueue((new Callback<List<Job>>() {
                @Override
                public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                    if(response.isSuccessful() && response.body().size() > 0) {
                        Log.e("SearchDataReceived:", "true");
                        List<Job> jobs = response.body();
                        arrayList.clear();
                        if(jobs != null && jobs.size() > 0) {
                            for(Job job : jobs) {
                                String id = String.valueOf(job.getJobID());
                                Log.e("Job ID: ", id);
                                Post post = new Post(id, job.getJobTitle(), job.getPostDate(), job.getDescription(), job.getSalaryRange(), job.getRequirements());
                                arrayList.add(post);
                            }
                            Log.e("arrayListSize: ", String.valueOf(arrayList.size()));
                            postAdapter.notifyDataSetChanged();
                        }

                    }
                    else {
                        arrayList.clear();
                        postAdapter.notifyDataSetChanged();
                        Log.e("SearchDataReceived:", "false");
                    }
                }

                @Override
                public void onFailure(Call<List<Job>> call, Throwable t) {

                }
            }));
        } catch (Exception ex) {
            Log.e("prepareSearchData", ex.getMessage());
        }

    }
    private void fillData(ArrayList<Post> array) {
        postAdapter = new PostAdapter(getApplicationContext(), array, new PostAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
                intent.putExtra("jobID", array.get(position).getJobID());
                startActivity(intent);
            }

            @Override
            public void onItemHold(int position) {

            }
        });
        recyclerView = binding.ListSearchItems;
        recyclerView.setAdapter(postAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }
}
