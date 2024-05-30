package com.example.jobfinder;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobfinder.databinding.ActivityCvBinding;

public class CVActivity extends AppCompatActivity {
    private ActivityCvBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCvBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.cvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
