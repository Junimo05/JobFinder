package com.example.jobfinder.data.api;

import com.example.jobfinder.data.model.Employer;
import com.example.jobfinder.data.model.Job;
import com.example.jobfinder.data.model.JobGroup;
import com.example.jobfinder.data.model.Application;
import com.example.jobfinder.data.model.Employee;
import com.example.jobfinder.data.model.User;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;
public interface ApiInterface {

    //Users

        @GET("users/forgotPassword/{email}")
        Call<User> forgotPassword();

        @GET("users")
        Call<List<User>> getUsers();

        @GET("users/{id}")
        Call<User> getUserById(String id);

        @POST("users")
        Call<User> createUser(@Body User user);

        @PATCH("users/{id}")
        Call<User> updateUser(@Path("id") String id, @Body User user);

        @DELETE("users/{id}")
        Call<User> deleteUser(String id);

    //Application
        @GET("applications")
        Call<List<Application>> getApplications();

        @GET("applications/{id}")
        Call<Application> getApplicationById(String id);

        @POST("applications")
        Call<Application> createApplication(@Body Application application);

        @PATCH("applications/{id}")
        Call<Application> updateApplication(@Path("id") String id, @Body Application application);

        @DELETE("applications/{id}")
        Call<Application> deleteApplication(String id);

    //Jobs
        @GET("jobs")
        Call<List<Job>> getJobs();

        @GET("jobs/{id}")
        Call<Job> getJobById(String id);

        @GET("jobs/{title}")
        Call<Job> getJobByTitle(String title);

        @GET("jobs/{group}")
        Call<Job> getJobByJobGroup(String group);

        @POST("jobs")
        Call<Job> createJob(@Body Job job);

        @PATCH("jobs/{id}")
        Call<Job> updateJob(@Body Job job);

        @DELETE("jobs/{id}")
        Call<Job> deleteJob(String id);

    //JobGroups
        @GET("jobgroups")
        Call<List<JobGroup>> getJobGroups();

        @GET("jobgroups/{id}")
        Call<JobGroup> getJobGroupById(String id);

        @POST("jobgroups")
        Call<JobGroup> createJobGroup(@Body JobGroup jobGroup);

        @PATCH("jobgroups/{id}")
        Call<JobGroup> updateJobGroup(@Body JobGroup jobGroup);

        @DELETE("jobgroups/{id}")
        Call<JobGroup> deleteJobGroup(String id);
    //Employees
        @GET("employees")
        Call<List<Employee>> getEmployees();

        @GET("employees/{id}")
        Call<Employee> getEmployeeById(String id);

        @POST("employees")
        Call<Employee> createEmployee(@Body Employee employee);

        @PATCH("employees/{id}")
        Call<Employee> updateEmployee(@Body Employee employee);

        @DELETE("employees/{id}")
        Call<Employee> deleteEmployee(String id);

    //Employers
        @GET("employers")
        Call<List<Employer>> getEmployers();

        @GET("employers/{id}")
        Call<Employer> getEmployerById(String id);

        @POST("employers")
        Call<Employer> createEmployer(@Body Employer employer);

        @PATCH("employers/{id}")
        Call<Employer> updateEmployer(@Body Employer employer);

        @DELETE("employers/{id}")
        Call<Employer> deleteEmployer(String id);
}
