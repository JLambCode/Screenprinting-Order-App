package com.example.customerordertracker.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Department;
import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.DepartmentViewModel;
import com.example.customerordertracker.ViewModel.EmployeeViewModel;
import com.example.customerordertracker.ui.EmployeeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import static com.example.customerordertracker.Utilities.Constants.EDIT_EMPLOYEE_ACTIVITY_REQUEST_CODE;

public class EmployeeInfo extends AppCompatActivity {

    private EmployeeViewModel mEmployeeViewModel;
    private DepartmentViewModel mDepartmentViewModel;
    private TextView mFName;
    private TextView mLName;
    private TextView mEmail;
    private TextView mPhone;
    private TextView mUsername;
    private TextView mDepartment;
    private int mAdmin;
    private int employeeId;
    private int departmentId;
    private RadioGroup mRadAdminGroup;
    private RadioButton mRadAdmin;
    private RadioButton mRadEmp;
    private List<Employee> employeeData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);
        setTitle("Employee Info");
        mEmployeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        mDepartmentViewModel = new ViewModelProvider(this).get(DepartmentViewModel.class);
        mFName = findViewById(R.id.outputFName);
        mLName = findViewById(R.id.outputLName);
        mEmail = findViewById(R.id.outputEmail);
        mPhone = findViewById(R.id.outputPhone);
        mUsername = findViewById(R.id.outputUsername);
        mDepartment = findViewById(R.id.outputDepartment);
        employeeId = getIntent().getIntExtra("employeeId", 0);
        mFName.setText(getIntent().getStringExtra("employeeFName"));
        mLName.setText(getIntent().getStringExtra("employeeLName"));
        mEmail.setText(getIntent().getStringExtra("employeeEmail"));
        mPhone.setText(getIntent().getStringExtra("employeePhone"));
        mUsername.setText(getIntent().getStringExtra("employeeUsername"));
        mDepartmentViewModel.getAllDepartments().observe(this, departments -> {
            for(Department p:departments){
                if(p.getDeptID()==getIntent().getIntExtra("employeeDepartment", 0)){
                    mDepartment.setText(p.getName());
                    departmentId = p.getDeptID();
                }
            }
        });
        mAdmin = getIntent().getIntExtra("employeeAdmin", 0);
        mRadAdminGroup = findViewById(R.id.radioGroupAdmin);
        mRadAdmin = findViewById(R.id.radAdmin);
        mRadEmp = findViewById(R.id.radEmp);

        if (mAdmin == 1){
            mRadAdmin.setChecked(true);
        } else {
            mRadEmp.setChecked(true);
        }

        mRadAdmin.setClickable(false);
        mRadEmp.setClickable(false);

        FloatingActionButton delete = findViewById(R.id.btnDelete);
        delete.setOnClickListener(view -> {
            mEmployeeViewModel.getAllEmployees().observe(this, employees -> {
                for(Employee employee: employees) {
                    if (employee.getEmpID() == employeeId) {
                        if (employee.getEmpID() != 1) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("Confirmation");
                            builder.setMessage("Are you sure you want to delete this employee?");
                            builder.setCancelable(true);
                            builder.setPositiveButton("Delete", (dialog, which) -> {
                                mEmployeeViewModel.delete(employee);
                                Toast.makeText(getApplicationContext(), "Employee deleted", Toast.LENGTH_SHORT).show();
                                finish();
                            });
                            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        } else {
                            Toast.makeText(getApplicationContext(), "This employee cannot be deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        });

        FloatingActionButton edit = findViewById(R.id.btnEdit);
        edit.setOnClickListener(view -> {
            Intent intent = new Intent(this, EmployeeEdit.class);
            String fName = mFName.getText().toString();
            String lName = mLName.getText().toString();
            String email = mEmail.getText().toString();
            String phone = mPhone.getText().toString();
            String username = mUsername.getText().toString();
            int admin = mAdmin;
            int employeeId = getIntent().getIntExtra("employeeId", 0);
            intent.putExtra("employeeId", employeeId);
            intent.putExtra("employeeFName", fName);
            intent.putExtra("employeeLName", lName);
            intent.putExtra("employeeEmail", email);
            intent.putExtra("employeePhone", phone);
            intent.putExtra("employeeAdmin", admin);
            intent.putExtra("departmentId", departmentId);
            intent.putExtra("employeeUsername", username);

            startActivityForResult(intent, EDIT_EMPLOYEE_ACTIVITY_REQUEST_CODE);
        });

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if(requestCode == EDIT_EMPLOYEE_ACTIVITY_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                mFName.setText(replyIntent.getStringExtra("employeeFName"));
                mLName.setText(replyIntent.getStringExtra("employeeLName"));
                mEmail.setText(replyIntent.getStringExtra("employeeEmail"));
                mPhone.setText(replyIntent.getStringExtra("employeePhone"));
                mUsername.setText(replyIntent.getStringExtra("employeeUsername"));
                mDepartmentViewModel.getAllDepartments().observe(this, departments -> {
                    for(Department p:departments){
                        if(p.getDeptID()==replyIntent.getIntExtra("employeeDepartment", 0)){
                            mDepartment.setText(p.getName());
                        }
                    }
                });
                mAdmin = replyIntent.getIntExtra("employeeAdmin", 0);
                mRadAdminGroup = findViewById(R.id.radioGroupAdmin);
                mRadAdmin = findViewById(R.id.radAdmin);
                mRadEmp = findViewById(R.id.radEmp);

                if (mAdmin == 1){
                    mRadAdmin.setChecked(true);
                } else {
                    mRadEmp.setChecked(true);
                }
                int employeeId = replyIntent.getIntExtra("employeeId",0);
                String fName = replyIntent.getStringExtra("employeeFName");
                String lName = replyIntent.getStringExtra("employeeLName");
                String email = replyIntent.getStringExtra("employeeEmail");
                String phone = replyIntent.getStringExtra("employeePhone");
                String username = replyIntent.getStringExtra("employeeUsername");
                String password = replyIntent.getStringExtra("employeePassword");
                int departmentId = replyIntent.getIntExtra("employeeDepartmentId",0);
                int admin = replyIntent.getIntExtra("employeeAdmin",0);
                Employee updatedEmployee = new Employee(employeeId, fName, lName, email, phone, departmentId, admin, username, password);
                mEmployeeViewModel.update(updatedEmployee);
                final EmployeeAdapter adapter = new EmployeeAdapter(employeeData, this);
                adapter.notifyDataSetChanged();
                mEmployeeViewModel.getAllEmployees().observe(this, employees -> { adapter.setEmployees(employees);});
                Toast.makeText(getApplicationContext(), "Employee successfully updated", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radAdminEdit:
                if (checked)
                    mAdmin = 1;
                break;
            case R.id.radEmpEdit:
                if (checked)
                    mAdmin = 0;
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
