package com.example.customerordertracker.ViewModel;

import android.app.Application;

import com.example.customerordertracker.Database.Repository;
import com.example.customerordertracker.Entities.Department;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DepartmentViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Department>> mAllDepartments;

    public DepartmentViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
        mAllDepartments = mRepository.getAllDepartments();
    }

    public LiveData<List<Department>> getAllDepartments() {return mAllDepartments;}

    public void insert(Department department) {mRepository.insertDepartment(department);}

    public void update(Department department) {mRepository.updateDepartment(department);}

    public void delete(Department department) {mRepository.deleteDepartment(department);}

    public int lastId() { return mAllDepartments.getValue().size();}

    public void deleteAllClients() {mRepository.deleteAllDepartments();}

}
