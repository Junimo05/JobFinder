package viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import data.model.JobGroup;
import data.repository.JobGroupRepo;

public class JobGroupViewModel extends ViewModel {

    private JobGroupRepo jobGroupRepo;
    private MutableLiveData<List<JobGroup>> listJobGroups = new MutableLiveData<>();
    public JobGroupViewModel(JobGroupRepo jobGroupRepo){
        this.jobGroupRepo = jobGroupRepo;
    }

    public LiveData<List<JobGroup>> getJobGroupsRepository(){
        try {
            if(listJobGroups==null){
                listJobGroups = jobGroupRepo.requestAllJobGroups();
            }
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
