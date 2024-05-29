package com.example.jobfinder.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jobfinder.LoginActivity;
import com.example.jobfinder.MyApplication;
import com.example.jobfinder.R;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.User;
import com.example.jobfinder.databinding.FragmentRegister2EmployerBinding;
import com.example.jobfinder.model.EmployerInput;

import retrofit2.Call;
import retrofit2.Response;

public class Register2_Employer extends Fragment {

    private FragmentRegister2EmployerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegister2EmployerBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiInterface apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.inputCompanyName.getText() == null || binding.inputCompanyName.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Vui lòng nhập tên công ty", Toast.LENGTH_SHORT).show();
                }else if(binding.inputIndustry.getText() == null || binding.inputIndustry.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập ngành nghề", Toast.LENGTH_SHORT).show();
                }else if(binding.inputEmail.getText() == null || binding.inputEmail.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                }else if(binding.inputPhone.getText() == null || binding.inputPhone.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }

                String username = getArguments().getString("username");
                String password = getArguments().getString("password");
                String companyName = binding.inputCompanyName.getText().toString();
                String industry = binding.inputIndustry.getText().toString();
                String email = binding.inputEmail.getText().toString();
                String phone = binding.inputPhone.getText().toString();

                // Call the API to create an employer

                EmployerInput employerInput = new EmployerInput(username, password, companyName, industry, email, phone);

                try {
                    Call<User> call = apiInterface.createEmployer(employerInput);
                    call.enqueue(new retrofit2.Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Error error){
                    error.printStackTrace();
                }

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });
    }
}