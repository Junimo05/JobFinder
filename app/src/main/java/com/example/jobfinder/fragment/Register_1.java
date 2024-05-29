package com.example.jobfinder.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.jobfinder.LoginActivity;
import com.example.jobfinder.R;
import com.example.jobfinder.databinding.FragmentRegister1Binding;

public class Register_1 extends Fragment {

    private FragmentRegister1Binding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegister1Binding.inflate(inflater, container, false);
        String[] choices = {"Employee", "Employer"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, choices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.typeAccount.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String typeUser = null;
                if (binding.typeAccount.getSelectedItem() == null || binding.typeAccount.getSelectedItem().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Vui lòng chọn vai trò", Toast.LENGTH_SHORT).show();
                } else {
                    typeUser =  binding.typeAccount.getSelectedItem().toString();
                }

                if (binding.etUsername.getText() == null || binding.etUsername.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    bundle.putString("username", binding.etUsername.getText().toString());
                }

                if (binding.etPassword.getText() == null || binding.etPassword.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    bundle.putString("password", binding.etPassword.getText().toString());
                }

                if (typeUser == "Employee"){
                    Register2_Employee reg2_fragment = new Register2_Employee();
                    reg2_fragment.setArguments(bundle);
                    getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, reg2_fragment).commit();
                } else if(typeUser == "Employer") {
                    Register2_Employer reg2_fragment = new Register2_Employer();
                    reg2_fragment.setArguments(bundle);
                    getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, reg2_fragment).commit();
                }

            }
        });

        binding.btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getContext(), LoginActivity.class);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}