package com.example.customerordertracker.DAO;

import com.example.customerordertracker.Entities.Employee;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_ADMIN;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_DEPARTMENTID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_PASSWORD;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_USERNAME;

@Dao
public interface EmployeeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEmployee(Employee employee);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateEmployee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);

    @Query("DELETE FROM " + TABLE_EMPLOYEE)
    int deleteAllEmployees();

    @Query("SELECT * FROM " + TABLE_EMPLOYEE + " WHERE " + TABLE_EMPLOYEE_COL_ID + " = :employeeId LIMIT 1")
    Employee getEmployee(int employeeId);

    @Query("SELECT * FROM " + TABLE_EMPLOYEE + " WHERE " + TABLE_EMPLOYEE_COL_DEPARTMENTID + " = :departmentId ORDER BY " + TABLE_EMPLOYEE_COL_ID)
    LiveData<List<Employee>> getRelatedEmployees(int departmentId);

    @Query("SELECT * FROM " + TABLE_EMPLOYEE + " ORDER BY " + TABLE_EMPLOYEE_COL_ID)
    LiveData<List<Employee>> getAllEmployees();

    @Query("SELECT COUNT(*) FROM " + TABLE_EMPLOYEE)
    int getEmployeeCount();

}
