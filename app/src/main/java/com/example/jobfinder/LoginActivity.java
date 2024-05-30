package com.example.jobfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.jobfinder.data.model.User;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.LoginUser;
import com.example.jobfinder.utils.LoginStatus;
import com.example.jobfinder.view.RegisterActivity;
import com.example.jobfinder.viewmodel.LoginViewModel;

import java.util.Objects;

import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //api
        ApiInterface apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        Button loginButton = findViewById(R.id.btn_login);

        loginButton.setOnClickListener(view -> {
            LoginUser loginUser = new LoginUser();
            loginUser.setUsername(((TextView) findViewById(R.id.et_username)).getText().toString());
            loginUser.setPassword(((TextView) findViewById(R.id.et_password)).getText().toString());

            try {
                Call<User> call = apiInterface.Login(loginUser);
                call.enqueue(new retrofit2.Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                        if (response.isSuccessful() && response.body() != null){
                            User user = response.body();
                            if (user.getUserID() != null && user.getUsername() != null && user.getRole() != null){
                                //save user to shared preferences
                                SharedPreferences sharedPreferences = getSharedPreferences("LoginInfo", MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("user_id", user.getUserID());
                                myEdit.putString("user_name", user.getUsername());
                                myEdit.putString("user_role", user.getRole());
                                myEdit.apply();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(LoginActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }catch (Exception e){
                Toast.makeText(LoginActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });

        //handle register
        TextView btnRegister = findViewById(R.id.tv_SignUp);
        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
