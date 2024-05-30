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
import com.example.jobfinder.data.model.CodeResponse;
import com.example.jobfinder.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword_1 extends Fragment {
    private String code;

    private CodeResponse.User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView email = view.findViewById(R.id.fgPass_emailInput);
        TextView codeInput = view.findViewById(R.id.fgPass_codeInput);
        //set button
        Button btn_back = view.findViewById(R.id.fgPass_backButton);
        Button btn_sendEmail = view.findViewById(R.id.btn_sendEmail);
        Button btn_submit = view.findViewById(R.id.fgPass_sendButton);

        //set on click listener
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btn_sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString();
                ApiInterface apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);
                try {
                    Call<CodeResponse> call = apiInterface.forgotPassword(emailStr);
                    call.enqueue(new Callback<CodeResponse>() {
                        @Override
                        public void onResponse(Call<CodeResponse> call, Response<CodeResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                CodeResponse codeResponse = response.body();
                                user = codeResponse.getUser();
                                code = codeResponse.getCode();
                                Toast.makeText(getContext(), "Đã gửi email", Toast.LENGTH_SHORT).show();
//                                Log.e("ForgotPassword_1", "Code: " + code);
                            } else {
                                Toast.makeText(getContext(), "Email không tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CodeResponse> call, Throwable t) {
                            Toast.makeText(getContext(), "Lỗi mạng", Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });
                }catch (Exception e){
//                    Log.e("ForgotPassword_1", "onClick: ", e);
                    e.printStackTrace();
                }
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (code != null && !code.isEmpty() && code.equals(codeInput.getText().toString())){
                    Bundle bundle = new Bundle();
                    bundle.putString("code", code);
                    bundle.putString("userID", String.valueOf(user.getUserID()));
                    Fragment fragment = new ForgotPassword_2();
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                } else {
                    Toast.makeText(getContext(), "Nhấn gửi email để lấy code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
