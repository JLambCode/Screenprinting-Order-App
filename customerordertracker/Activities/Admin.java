package com.example.customerordertracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.EmployeeViewModel;
import com.example.customerordertracker.ui.EmployeeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.customerordertracker.Utilities.Constants.NEW_EMPLOYEE_ACTIVITY_REQUEST_CODE;

public class Admin extends AppCompatActivity {

    private EmployeeViewModel mEmployeeViewModel;
    private List<Employee> employeeData = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_admin);
        mEmployeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        setTitle("Admin Menu");
        RecyclerView recyclerView = findViewById(R.id.users_list_recycler);
        final EmployeeAdapter adapter = new EmployeeAdapter(employeeData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mEmployeeViewModel.getAllEmployees().observe(this, employees -> { adapter.setEmployees(employees);});

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        FloatingActionButton departments = findViewById(R.id.btnDepartments);
        departments.setOnClickListener(view -> {
            Intent intent = new Intent(this, DepartmentList.class);
            startActivity(intent);
        });

        FloatingActionButton add = findViewById(R.id.btnAdd);
        add.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewEmployee.class);
            intent.putExtra("newEmployeeId", mEmployeeViewModel.lastId()+1);
            startActivityForResult(intent, NEW_EMPLOYEE_ACTIVITY_REQUEST_CODE);
        });

    }

    private void updateList() {
        mEmployeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.users_list_recycler);
        final EmployeeAdapter adapter = new EmployeeAdapter(employeeData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mEmployeeViewModel.getAllEmployees().observe(this, employees -> adapter.setEmployees(employees));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if (requestCode == NEW_EMPLOYEE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int employeeId = replyIntent.getIntExtra("employeeId",0);
            String fName = replyIntent.getStringExtra("employeeFName");
            String lName = replyIntent.getStringExtra("employeeLName");
            String email = replyIntent.getStringExtra("employeeEmail");
            String phone = replyIntent.getStringExtra("employeePhone");
            String username = replyIntent.getStringExtra("employeeUsername");
            String password = replyIntent.getStringExtra("employeePassword");
            int departmentId = replyIntent.getIntExtra("employeeDepartmentId",0);
            int admin = replyIntent.getIntExtra("employeeAdmin",0);
            Employee newEmployee = new Employee(employeeId, fName, lName, email, phone, departmentId, admin, username, password);
            mEmployeeViewModel.update(newEmployee);
            Toast.makeText(getApplicationContext(), "Employee successfully added", Toast.LENGTH_LONG).show();
            final EmployeeAdapter adapter = new EmployeeAdapter(employeeData, this);
            adapter.notifyDataSetChanged();
            mEmployeeViewModel.getAllEmployees().observe(this, employees -> { adapter.setEmployees(employees);});
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
