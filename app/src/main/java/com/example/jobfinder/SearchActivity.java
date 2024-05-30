package com.example.jobfinder;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobfinder.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {
    private ActivitySearchBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
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
}
