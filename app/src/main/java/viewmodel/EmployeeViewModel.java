package viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import data.model.Employee;
import data.repository.EmployeeRepo;

public class EmployeeViewModel extends ViewModel {
    private EmployeeRepo employeeRepo;
    private MutableLiveData<List<Employee>> listEmployees = new MutableLiveData<>();;
    public EmployeeViewModel(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    public LiveData<List<Employee>> getEmployeesRepository(){
        try {
            if(listEmployees==null){
                listEmployees = employeeRepo.requestAllEmployees();
            }
        } catch (Exception ex) {
            System.out.println("User viewmodel" + ex.getMessage());
        }
        return listEmployees;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
