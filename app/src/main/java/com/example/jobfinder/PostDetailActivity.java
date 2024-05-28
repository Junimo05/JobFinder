package com.example.jobfinder;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobfinder.databinding.ActivityPostDetailBinding;

public class PostDetailActivity extends AppCompatActivity {
    private ActivityPostDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String Salary = getIntent().getStringExtra("Salary");
//        String Experience = getIntent().getStringExtra("Experience");
//        String Description = getIntent().getStringExtra("Description");
        binding = ActivityPostDetailBinding.inflate(getLayoutInflater());
//        binding.PDSalary.setText(Salary);
//        binding.PDExperience.setText(Experience);
//        binding.PDDescription.setText(Description);
        binding.PDBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setContentView(binding.getRoot());
    }
}
