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
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.User;
import com.example.jobfinder.databinding.FragmentRegister2EmployeeBinding;
import com.example.jobfinder.model.EmployeeInput;

import retrofit2.Call;


public class Register2_Employee extends Fragment {

    private FragmentRegister2EmployeeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegister2EmployeeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiInterface apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.inputName.getText() == null || binding.inputName.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
                } else if (binding.inputAge.getText() == null || binding.inputAge.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập tuổi", Toast.LENGTH_SHORT).show();
                } else if (binding.inputPhone.getText() == null || binding.inputPhone.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                } else if (binding.inputEmail.getText() == null || binding.inputEmail.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                }


                String username = getArguments().getString("username");
                String password = getArguments().getString("password");
                String name = binding.inputName.getText().toString();
                String age = binding.inputAge.getText().toString();
                String phone = binding.inputPhone.getText().toString();
                String email = binding.inputEmail.getText().toString();
                String aboutMe = binding.inputAboutMe.getText().toString();
                String hobbies = binding.inputHobbies.getText().toString();

                // Create a new Employee object
                EmployeeInput employee = new EmployeeInput(username, password ,name, age, phone, email, aboutMe, hobbies);

                // Call the API to register the employee

                try {
                    Call<User> call = apiInterface.createEmployee(employee);
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
                }catch (Error error){
                    error.printStackTrace();
                }

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }
}