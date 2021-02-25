package com.example.customerordertracker.ViewModel;

import android.app.Application;

import com.example.customerordertracker.Database.Repository;
import com.example.customerordertracker.Entities.Employee;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EmployeeViewModel extends AndroidViewModel {

    public int departmentId;
    private Repository mRepository;
    private LiveData<List<Employee>> mAllEmployees;
    private LiveData<List<Employee>> mRelatedEmployees;

    public EmployeeViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
        mAllEmployees = mRepository.getAllEmployees();
        //mRelatedEmployees = mRepository.getRelatedEmployees(departmentId);
    }

    public LiveData<List<Employee>> getRelatedEmployees(int departmentId){
        return mRepository.getRelatedEmployees(departmentId);
    }

    public LiveData<List<Employee>> getAllEmployees() {return mAllEmployees;}

    public void insert(Employee employee) {mRepository.insertEmployee(employee);}

    public void update(Employee employee) {mRepository.updateEmployee(employee);}

    public void delete(Employee employee) {mRepository.deleteEmployee(employee);}

    public int lastId() { return mAllEmployees.getValue().size();}

    public void deleteAllEmployees() {mRepository.deleteAllEmployees();}

}
