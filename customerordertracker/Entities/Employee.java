package com.example.customerordertracker.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static com.example.customerordertracker.Utilities.Constants.TABLE_DEPARTMENT_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_ADMIN;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_DEPARTMENTID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_EMAIL;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_FNAME;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_LNAME;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_PASSWORD;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_PHONE;
import static com.example.customerordertracker.Utilities.Constants.TABLE_EMPLOYEE_COL_USERNAME;

@Entity(tableName = TABLE_EMPLOYEE,
        foreignKeys = {@ForeignKey(
                entity = Department.class,
                parentColumns = TABLE_DEPARTMENT_COL_ID,
                childColumns = TABLE_EMPLOYEE_COL_DEPARTMENTID,
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = TABLE_EMPLOYEE_COL_DEPARTMENTID)}
        )

public class Employee {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=TABLE_EMPLOYEE_COL_ID)
    public int empID;

    @ColumnInfo(name=TABLE_EMPLOYEE_COL_FNAME)
    @NonNull
    private String fName;

    @ColumnInfo(name=TABLE_EMPLOYEE_COL_LNAME)
    @NonNull
    private String lName;

    @ColumnInfo(name=TABLE_EMPLOYEE_COL_EMAIL)
    @NonNull
    private String email;

    @ColumnInfo(name=TABLE_EMPLOYEE_COL_PHONE)
    @NonNull
    private String phone;

    @ColumnInfo(name=TABLE_EMPLOYEE_COL_DEPARTMENTID)
    @NonNull
    public int departmentId;

    @ColumnInfo(name=TABLE_EMPLOYEE_COL_ADMIN)
    @NonNull
    public int admin;

    @ColumnInfo(name=TABLE_EMPLOYEE_COL_USERNAME)
    @NonNull
    public String username;

    @ColumnInfo(name=TABLE_EMPLOYEE_COL_PASSWORD)
    @NonNull
    public String password;

    public Employee(int empID, String fName, String lName, String email, String phone, int departmentId, int admin, String username, String password) {
        this.empID = empID;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.departmentId = departmentId;
        this.admin = admin;
        this.username = username;
        this.password = password;
    }

    @Ignore
    public Employee(int empID, String fName, int departmentId) {
        this.empID = empID;
        this.fName = fName;
        this.departmentId = departmentId;
    }

    @Ignore
    public Employee(int empID, String fName) {
        this.empID = empID;
        this.fName = fName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", deptID=" + departmentId +
                ", admin=" + admin +
                ", username='" + username + '\'' +
                '}';
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
