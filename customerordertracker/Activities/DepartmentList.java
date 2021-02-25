package com.example.customerordertracker.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.customerordertracker.Entities.Department;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.DepartmentViewModel;
import com.example.customerordertracker.ui.DepartmentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.customerordertracker.Utilities.Constants.NEW_DEPARTMENT_ACTIVITY_REQUEST_CODE;

public class DepartmentList extends AppCompatActivity {

    private DepartmentViewModel mDepartmentViewModel;
    private List<Department> departmentData = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_department_list);
        mDepartmentViewModel = new ViewModelProvider(this).get(DepartmentViewModel.class);
        setTitle("Department List");

        RecyclerView recyclerView = findViewById(R.id.department_list_recycler);
        final DepartmentAdapter adapter = new DepartmentAdapter(departmentData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        mDepartmentViewModel.getAllDepartments().observe(this, departments -> {
            FloatingActionButton add = findViewById(R.id.btnAdd);
            add.setOnClickListener(view -> {
                Intent intent = new Intent(this, NewDepartment.class);
                intent.putExtra("departmentId", mDepartmentViewModel.lastId()+1);
                startActivityForResult(intent, NEW_DEPARTMENT_ACTIVITY_REQUEST_CODE);
            });
        });
    }

    private void updateList() {
        mDepartmentViewModel = new ViewModelProvider(this).get(DepartmentViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.department_list_recycler);
        final DepartmentAdapter adapter = new DepartmentAdapter(departmentData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mDepartmentViewModel.getAllDepartments().observe(this, departments -> adapter.setDepartments(departments));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if (requestCode == NEW_DEPARTMENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int departmentId = replyIntent.getIntExtra("departmentId", 0);
            String name = replyIntent.getStringExtra("departmentName");
            Department department = new Department(departmentId, name);
            mDepartmentViewModel.insert(department);
            final DepartmentAdapter adapter = new DepartmentAdapter(departmentData, this);
            adapter.notifyDataSetChanged();
            mDepartmentViewModel.getAllDepartments().observe(this, departments -> adapter.setDepartments(departments));
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
