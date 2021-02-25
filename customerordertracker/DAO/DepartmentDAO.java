package com.example.customerordertracker.DAO;

import com.example.customerordertracker.Entities.Department;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import static com.example.customerordertracker.Utilities.Constants.TABLE_DEPARTMENT;
import static com.example.customerordertracker.Utilities.Constants.TABLE_DEPARTMENT_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE;

@Dao
public interface DepartmentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDepartment(Department department);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateDepartment(Department department);

    @Delete
    void deleteDepartment(Department department);

    @Query("DELETE FROM " + TABLE_DEPARTMENT)
    int deleteAllDepartments();

    @Query("SELECT * FROM " + TABLE_DEPARTMENT + " WHERE " + TABLE_DEPARTMENT_COL_ID + " = :departmentId LIMIT 1")
    Department getDepartment(int departmentId);

    @Query("SELECT * FROM " + TABLE_DEPARTMENT + " ORDER BY " + TABLE_DEPARTMENT_COL_ID)
    LiveData<List<Department>> getAllDepartments();

    @Query("SELECT COUNT(*) FROM " + TABLE_DEPARTMENT)
    int getDepartmentCount();

}
