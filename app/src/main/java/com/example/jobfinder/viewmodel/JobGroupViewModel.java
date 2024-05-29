package com.example.jobfinder.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jobfinder.data.model.JobGroup;
import com.example.jobfinder.data.repository.JobGroupRepo;

import java.util.List;

public class JobGroupViewModel extends ViewModel {

    public JobGroupRepo jobGroupRepo;
    private MutableLiveData<List<JobGroup>> listJobGroups = new MutableLiveData<>();

    public JobGroupViewModel(){
        this.jobGroupRepo = new JobGroupRepo();
    }
    public JobGroupViewModel(JobGroupRepo jobGroupRepo){
        this.jobGroupRepo = jobGroupRepo;
    }

    public LiveData<List<JobGroup>> getJobGroups() {
        return listJobGroups;
    }

    public LiveData<List<JobGroup>> getJobGroupsRepository(){
        try {
            listJobGroups = jobGroupRepo.requestAllJobGroups();
        } catch (Exception ex) {
            System.out.println("User viewmodel" + ex.getMessage());
        }
        return listJobGroups;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
