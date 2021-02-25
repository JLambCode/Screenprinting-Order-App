package com.example.customerordertracker.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Department;
import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.DepartmentViewModel;
import com.example.customerordertracker.ui.DepartmentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class DepartmentEdit extends AppCompatActivity {

    private DepartmentViewModel mDepartmentViewModel;
    private EditText mDepartmentName;
    private int departmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_edit);
        setTitle("Edit Department");
        mDepartmentViewModel = new ViewModelProvider(this).get(DepartmentViewModel.class);
        mDepartmentName = findViewById(R.id.inputDeptName);
        mDepartmentName.setText(getIntent().getStringExtra("departmentName"));

        departmentId = getIntent().getIntExtra("departmentId", 0);

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        FloatingActionButton delete = findViewById(R.id.btnDelete);
        delete.setOnClickListener(view -> {
            mDepartmentViewModel.getAllDepartments().observe(this, departments -> {
                for(Department p: departments){
                    if(p.getDeptID() == departmentId){
                        if(p.getDeptID() > 6) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("Confirmation");
                            builder.setMessage("Are you sure you want to delete this department?");
                            builder.setCancelable(true);
                            builder.setPositiveButton("Delete", (dialog, which) -> {
                                mDepartmentViewModel.delete(p);
                                Toast.makeText(getApplicationContext(), "Department deleted", Toast.LENGTH_SHORT).show();
                                finish();
                            });
                            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "This department cannot be deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        });

        FloatingActionButton save = findViewById(R.id.btnSave);
        save.setOnClickListener(view -> {
            if (getIntent().getStringExtra("departmentId") != null) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mDepartmentName.getText())) {
                    Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    int id = getIntent().getIntExtra("departmentId", 0);
                    String name = mDepartmentName.getText().toString();

                    replyIntent.putExtra("departmentId", id);
                    replyIntent.putExtra("departmentName", name);

                    Department newDepartment = new Department(id, name);
                    mDepartmentViewModel.update(newDepartment);
                    setResult(RESULT_OK, replyIntent);
                    Toast.makeText(getApplicationContext(), "Department successfully updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
