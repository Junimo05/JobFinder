package com.example.jobfinder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fragment.Fragment_Account;
import fragment.Fragment_Main;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        fragmentManager = getSupportFragmentManager();
        bottomNavigationView.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.navigation_home_ver2:
                            //Xu ly home navigation
                            Fragment_Main fragment = new Fragment_Main();
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.fragment_container, fragment);
                            transaction.commit();
                            return true;
                        case R.id.navigation_profile_ver2:
                            //Xu ly profile navigation
                            Fragment_Account fragment1 = new Fragment_Account();
                            FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                            transaction1.replace(R.id.fragment_container, fragment1);
                            transaction1.commit();
                            return true;
                        case R.id.navigation_notes_ver2:
                            return true;
                        case R.id.navigation_notifications_ver2:
                            return true;
                        case R.id.navigation_settings_ver2:
                            return true;
                    }
                    return false;
                }

        );
        Fragment_Main fragment = new Fragment_Main();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

    }
}