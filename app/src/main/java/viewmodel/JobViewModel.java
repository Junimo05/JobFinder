package viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import data.model.Job;
import data.repository.JobRepo;

public class JobViewModel extends ViewModel {
    private JobRepo jobRepo;
    private MutableLiveData<List<Job>> listJobs = new MutableLiveData<>();
    private MutableLiveData<Job> jobListByGroup = new MutableLiveData<>();
    public JobViewModel(JobRepo jobRepo){
        this.jobRepo = jobRepo;
    }

    public LiveData<List<Job>> getJobsRepository(){
        try {
            if(listJobs==null){
                listJobs = jobRepo.requestAllJobs();
            }
        } catch (Exception ex) {
            System.out.println("User viewmodel" + ex.getMessage());
        }
        return listJobs;
    }

    public LiveData<Job> getJobByJobGroup(String jobGroup){
        try {
            if(jobListByGroup==null){
                jobListByGroup = jobRepo.requestJobByJobGroup(jobGroup);
            }
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