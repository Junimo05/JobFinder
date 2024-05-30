package com.example.jobfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.jobfinder.data.model.User;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.LoginUser;
import com.example.jobfinder.view.RegisterActivity;

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

        loginButton.setOnClickListener(v -> {
            LoginUser loginUser = new LoginUser();
            loginUser.setUsername(((TextView) findViewById(R.id.et_username)).getText().toString());
            loginUser.setPassword(((TextView) findViewById(R.id.et_password)).getText().toString());

            try {
                Call<User> call = apiInterface.Login(loginUser);
                call.enqueue(new retrofit2.Callback<User>() {
                    @Override
                    public void onResponse(Call<com.example.jobfinder.data.model.User> call, retrofit2.Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();
                            if (user != null) {
                                //save user to shared preferences
                                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("user_id", user.getUserID());
                                myEdit.putString("user_name", user.getUsername());
                                myEdit.putString("user_role", user.getRole());
                                myEdit.apply();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }catch (Error ex){
                ex.printStackTrace();
            }
        });

        TextView btnRegister = findViewById(R.id.tv_SignUp);
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }
}
