package com.example.jobfinder.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jobfinder.MyApplication;
import com.example.jobfinder.R;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.ChangePassword;
import com.example.jobfinder.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword_2 extends Fragment {
    private String code;
    private String userID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirmed_password, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView returnLogin = view.findViewById(R.id.returnToLogin);
        TextView passwordInput = view.findViewById(R.id.passwordInput);
        TextView confirmPasswordInput = view.findViewById(R.id.confirmPasswordInput);

        ApiInterface apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        returnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            code = bundle.getString("code");
            userID = bundle.getString("userID");
        }

        Button btn_confirm = view.findViewById(R.id.confirmButton);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                String password = passwordInput.getText().toString();
                String confirmPassword = confirmPasswordInput.getText().toString();

                if (password.equals(confirmPassword)) {
                    // Call the API to update the password
                    ChangePassword user = new ChangePassword(userID, password);
                    Call<User> call = apiInterface.updateUser(userID, user);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()) {
                                User user = response.body();
                                Log.e("ForgotPassword_2", user.getUserID());
                                Toast.makeText(getContext(), "Password updated successfully", Toast.LENGTH_SHORT).show();
                                getActivity().onBackPressed();
                            } else {
                                Toast.makeText(getContext(), "Failed to update password", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getContext(), "Failed to update password", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                }
           }}
        );
    }
}
