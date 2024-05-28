package com.example.jobfinder.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jobfinder.data.model.Employer;
import com.example.jobfinder.data.repository.EmployerRepo;

import java.util.List;

public class EmployerViewModel extends ViewModel {
    private EmployerRepo employerRepo;
    private MutableLiveData<List<Employer>> listEmployers = new MutableLiveData<>();
    public EmployerViewModel(EmployerRepo employerRepo){
        this.employerRepo = employerRepo;
    }

    public LiveData<List<Employer>> getEmployersRepository(){
        try {
            if(listEmployers==null){
                listEmployers = employerRepo.requestAllEmployers();
            }
        } catch (Exception ex) {
            System.out.println("User viewmodel" + ex.getMessage());
        }
        return listEmployers;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
