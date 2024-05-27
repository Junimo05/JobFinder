package viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import data.model.Application;
import data.repository.ApplicationRepo;

public class ApplicationViewModel extends ViewModel {
    private ApplicationRepo appRepo;

    private MutableLiveData<List<Application>> listApplications = new MutableLiveData<>();

    public ApplicationViewModel(ApplicationRepo appRepo){
        this.appRepo = appRepo;
    }

    public MutableLiveData<List<Application>> getApplicationsRepository(){
        try {
            if(listApplications==null){
                listApplications = appRepo.getApplications();
            }
        } catch (Exception ex) {
            System.out.println("User viewmodel" + ex.getMessage());
        }
        return listApplications;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
