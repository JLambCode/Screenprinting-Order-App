package com.example.customerordertracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Department;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.DepartmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NewDepartment extends AppCompatActivity {

    private EditText mDepartmentName;
    private DepartmentViewModel mDepartmentViewModel;
    int departmentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_department);
        setTitle("Add Department");
        mDepartmentViewModel = new ViewModelProvider(this).get(DepartmentViewModel.class);
        try {
            mDepartmentViewModel.getAllDepartments().observe(this, departments -> {
                List<Department> allDepartments = new ArrayList<>();
                for (Department p : departments) {
                    allDepartments.add(p);
                }
                departmentCount = allDepartments.size();
            });
        }catch (Exception e){
            departmentCount = 0;
        }
        mDepartmentName = findViewById(R.id.inputDeptName);

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        FloatingActionButton save = findViewById(R.id.btnSave);
        save.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(mDepartmentName.getText())) {
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_SHORT).show();
            }
            else {

                int departmentId = departmentCount + 1;
                String name = mDepartmentName.getText().toString();

                replyIntent.putExtra("departmentId", departmentId);
                replyIntent.putExtra("departmentName", name);

                Department newDepartment = new Department(departmentId, name);
                mDepartmentViewModel.insert(newDepartment);
                setResult(RESULT_OK, replyIntent);
                Toast.makeText(getApplicationContext(), "Department successfully added", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
