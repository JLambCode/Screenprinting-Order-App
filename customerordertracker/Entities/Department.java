package com.example.customerordertracker.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static com.example.customerordertracker.Utilities.Constants.TABLE_DEPARTMENT;
import static com.example.customerordertracker.Utilities.Constants.TABLE_DEPARTMENT_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_DEPARTMENT_COL_NAME;

@Entity(tableName = TABLE_DEPARTMENT,
        indices = {@Index(value = TABLE_DEPARTMENT_COL_ID)}
)

public class Department {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=TABLE_DEPARTMENT_COL_ID)
    public int deptID;

    @ColumnInfo(name=TABLE_DEPARTMENT_COL_NAME)
    @NonNull
    private String name;

    public Department(int deptID, String name) {
        this.deptID = deptID;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "DeptID=" + deptID +
                ", Name='" + name + '\'' +
                '}';
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
