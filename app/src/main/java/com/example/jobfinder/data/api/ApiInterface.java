package com.example.jobfinder.data.api;

import com.example.jobfinder.data.model.Employer;
import com.example.jobfinder.data.model.Job;
import com.example.jobfinder.data.model.JobGroup;
import com.example.jobfinder.data.model.Application;
import com.example.jobfinder.data.model.Employee;
import com.example.jobfinder.data.model.LoginUser;
import com.example.jobfinder.data.model.User;
import com.example.jobfinder.model.EmployeeInput;
import com.example.jobfinder.model.EmployerInput;

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

        @GET("users/getall")
        Call<List<User>> getUsers();

        @GET("users/searchID/{id}")
        Call<User> getUserById(@Path("id") String id);

        @GET("users/searchUsername/{username}")
        Call<User> getUserByUsername(@Path("username") String username);

        @POST("users/login")
        Call<User> Login(@Body LoginUser loginUser);

        @POST("users/upload/:{id}")
        Call<User> uploadImage(@Path("id") String id);

        @POST("users/create-employee")
        Call<User> createEmployee(@Body EmployeeInput employeeInput);

        @POST("users/create-employer")
        Call<User> createEmployer(@Body EmployerInput employerInput);

        @PATCH("users/{id}")
        Call<User> updateUser(@Path("id") String id, @Body User user);

        @DELETE("users/{id}")
        Call<User> deleteUser(String id);

    //Application
        @GET("applications/getall")
        Call<List<Application>> getApplications();

        @GET("applications/searchID/{id}")
        Call<Application> getApplicationById(@Path("id") String id);

        @GET("applications/searchUserID/{id}")
        Call<Application> getApplicationByUserId(@Path("id") String id);

        @POST("applications")
        Call<Application> createApplication(@Body Application application);

        @PATCH("applications/{id}")
        Call<Application> updateApplication(@Path("id") String id, @Body Application application);

        @DELETE("applications/{id}")
        Call<Application> deleteApplication(@Path("id") String id);

    //Jobs
        @GET("jobs/getall")
        Call<List<Job>> getJobs();

        @GET("jobs/searchID/{id}")
        Call<Job> getJobById(@Path("id") String id);

        @GET("jobs/searchTitle/{title}")
        Call<Job> getJobByTitle(@Path("title") String title);

        @GET("jobs/searchGroup/{group}")
        Call<List<Job>> getJobByJobGroup(@Path("group") String group);

        @POST("jobs/getall")
        Call<Job> createJob(@Body Job job);

        @PATCH("jobs/{id}")
        Call<Job> updateJob(@Path("id") String id, @Body Job job);

        @DELETE("jobs/{id}")
        Call<Job> deleteJob(@Path("id") String id);

    //JobGroups
        @GET("groups/getall")
        Call<List<JobGroup>> getJobGroups();

        @GET("groups/searchID/{id}")
        Call<JobGroup> getJobGroupById(@Path("id") String id);

        @GET("groups/searchTitle/{title}")
        Call<JobGroup> getJobGroupByTitle(@Path("title") String title);

        @POST("groups")
        Call<JobGroup> createJobGroup(@Body JobGroup jobGroup);

        @PATCH("groups/{id}")
        Call<JobGroup> updateJobGroup(@Path("id") String id, @Body JobGroup jobGroup);

    //Employees
        @GET("employees/getall")
        Call<List<Employee>> getEmployees();

        @GET("employees/searchID/{id}")
        Call<Employee> getEmployeeById(@Path("id") String id);

        @GET("employees/searchEmail/{email}")
        Call<Employee> getEmployeeByEmail(@Path("email") String email);

        @GET("employees/searchPhone/{phone}")
        Call<Employee> getEmployeeByPhone(@Path("phone") String phone);

        @PATCH("employees/{id}")
        Call<Employee> updateEmployee(@Path("id") String id, @Body Employee employee);

        @DELETE("employees/{id}")
        Call<Employee> deleteEmployee(@Path("id") String id);

    //Employers
        @GET("employers/getall")
        Call<List<Employer>> getEmployers();

        @GET("employers/searchID/{id}")
        Call<Employer> getEmployerById(@Path("id") String id);

        @GET("employers/searchName/{CompanyName}")
        Call<Employer> getEmployerByName(@Path("CompanyName") String CompanyName);

        @GET("employers/searchJobs/{id}")
        Call<Employer> getEmployerByJob(@Path("id") String id);

        @PATCH("employers/{id}")
        Call<Employer> updateEmployer(@Path("id") String id, @Body Employer employer);

        @DELETE("employers/{id}")
        Call<Employer> deleteEmployer(String id);
}
