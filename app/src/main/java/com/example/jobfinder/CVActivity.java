package com.example.jobfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.Employee;
import com.example.jobfinder.data.model.User;
import com.example.jobfinder.databinding.ActivityCvBinding;
import com.example.jobfinder.model.ApplicationInput;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CVActivity extends AppCompatActivity {
    private ActivityCvBinding binding;
    String id;
    String role;

    String jobName;
    String jobID;
    String employeeID;
    ApiInterface apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCvBinding.inflate(getLayoutInflater());
        SharedPreferences sharedPreferences = getSharedPreferences("LoginInfo", MODE_PRIVATE);
        id = sharedPreferences.getString("user_id", "default_value");
        role = sharedPreferences.getString("user_role", "default_value");
        jobName = getIntent().getStringExtra("jobName");
        jobID = getIntent().getStringExtra("jobID");
        prepareCVData(id);
        setContentView(binding.getRoot());
        binding.cvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = "Application for " + binding.cvJobValue.getText() + " job";
                ApplicationInput applicationInput = new ApplicationInput(Integer.valueOf(employeeID), Integer.valueOf(jobID), description);
                try {
                    Call<ApplicationInput> call = apiInterface.createApplication(applicationInput);
                    call.enqueue(new Callback<ApplicationInput>() {
                        @Override
                        public void onResponse(Call<ApplicationInput> call, Response<ApplicationInput> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Nộp đơn thành công", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Nộp đơn thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApplicationInput> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Nộp đơn thất bại", Toast.LENGTH_SHORT);

                        }
                    });
                } catch (Exception ex) {
                    Log.e("Error Create Application:", ex.getMessage());
                }

            }
        });
    }
    public void prepareCVData(String id) {
        try {
            Call<Employee> call = apiInterface.getEmployeeById(id);
            call.enqueue(new Callback<Employee>() {
                @Override
                public void onResponse(Call<Employee> call, Response<Employee> response) {
                    if(response.isSuccessful() && response.body() != null) {
                        Employee employee = response.body();
                        employeeID = String.valueOf(employee.getEmployeeID());
                        binding.cvCandidateValue.setText(employee.getName());
                        binding.cvAgeValue.setText(String.valueOf(employee.getAge()));
                        binding.cvMailValue.setText(employee.getEmail());
                        binding.cvPhoneValue.setText(employee.getPhone());
                        binding.cvJobValue.setText(jobName);
                        binding.cvCandidateValue.setEnabled(false);
                        binding.cvAgeValue.setEnabled(false);
                        binding.cvMailValue.setEnabled(false);
                        binding.cvPhoneValue.setEnabled(false);
                        binding.cvJobValue.setEnabled(false);
                    }
                }

                @Override
                public void onFailure(Call<Employee> call, Throwable t) {

                }
            });
        } catch (Exception ex) {
            Log.e("ErrorAPI Employee/ID: ", ex.getMessage());
        }
    }
}
