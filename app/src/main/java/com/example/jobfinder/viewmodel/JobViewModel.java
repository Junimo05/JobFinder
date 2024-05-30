package com.example.jobfinder.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jobfinder.data.model.Job;
import com.example.jobfinder.data.repository.JobRepo;

import java.util.List;

public class JobViewModel extends ViewModel {
    private JobRepo jobRepo;
    private MutableLiveData<List<Job>> listJobs = new MutableLiveData<>();
    private MutableLiveData<List<Job>> jobListByGroup = new MutableLiveData<>();
    public JobViewModel(JobRepo jobRepo){
        this.jobRepo = jobRepo;
    }

    public JobViewModel(){
        this.jobRepo = new JobRepo();
    }

    public LiveData<List<Job>> getJobListByGroup() {
        return jobListByGroup;
    }

    public LiveData<List<Job>> getJobsRepository(){
        try {
            listJobs = jobRepo.requestAllJobs();
        } catch (Exception ex) {
            System.out.println("User viewmodel" + ex.getMessage());
        }
        return listJobs;
    }

    public LiveData<List<Job>> getJobByJobGroup(String jobGroup){
        try {
            jobListByGroup = jobRepo.requestJobByJobGroup(jobGroup);
        } catch (Exception ex) {
            System.out.println("User viewmodel" + ex.getMessage());
        }
        return jobListByGroup;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}