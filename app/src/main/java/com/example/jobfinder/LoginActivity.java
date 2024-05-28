package com.example.jobfinder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jobfinder.data.model.LoginUser;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.btn_login);

        loginButton.setOnClickListener(v -> {
            LoginUser loginUser = new LoginUser();
            loginUser.setUsername(((TextView) findViewById(R.id.et_username)).getText().toString());
            loginUser.setPassword(((TextView) findViewById(R.id.et_password)).getText().toString());


            if (loginUser.getUsername().equals("admin") && loginUser.getPassword().equals("admin")) {
                // Navigate to main activity
            } else {

            }

        });
    }
}
