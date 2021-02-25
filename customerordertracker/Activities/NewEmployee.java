package com.example.customerordertracker.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.EmployeeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NewEmployee extends AppCompatActivity {

    private EmployeeViewModel mEmployeeViewModel;
    private EditText mFName;
    private EditText mLName;
    private EditText mEmail;
    private EditText mPhone;
    private EditText mUsername;
    private EditText mPassword;
    private RadioButton mRadAdmin;
    private RadioButton mRadEmp;
    private int mAdmin;
    private int employeeId;
    private int employeeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setTitle("New Employee");
        mEmployeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        try {
            mEmployeeViewModel.getAllEmployees().observe(this, employees -> {
                List<Employee> allEmployees = new ArrayList<>();
                for (Employee p : employees) {
                    allEmployees.add(p);
                }
                employeeCount = allEmployees.size();
            });
        } catch (Exception e){
            employeeCount = 0;
        }
        mFName = findViewById(R.id.inputFNameAdd);
        mLName = findViewById(R.id.inputLNameAdd);
        mEmail = findViewById(R.id.inputEmailAdd);
        mPhone = findViewById(R.id.inputPhoneAdd);
        mUsername = findViewById(R.id.inputUsernameAdd);
        mPassword = findViewById(R.id.inputPasswordAdd);
        mRadAdmin = findViewById(R.id.radAdmin);
        mRadEmp = findViewById(R.id.radEmp);

        mPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        Spinner mDepartment = findViewById(R.id.spinnerDept);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.departments_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDepartment.setAdapter(adapter);



        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        FloatingActionButton save = findViewById(R.id.btnSave);
        save.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mFName.getText()) ||
                    TextUtils.isEmpty(mLName.getText()) ||
                    TextUtils.isEmpty(mEmail.getText()) ||
                    TextUtils.isEmpty(mPhone.getText()) ||
                    TextUtils.isEmpty(mUsername.getText()) ||
                    TextUtils.isEmpty(mPassword.getText())) {
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_SHORT).show();
            }
            else {
                String fName = mFName.getText().toString();
                String lName = mLName.getText().toString();
                String email = mEmail.getText().toString();
                String phone = mPhone.getText().toString();
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                int departmentId = mDepartment.getSelectedItemPosition()+1;

                replyIntent.putExtra("employeeId", ++employeeCount);
                replyIntent.putExtra("employeeFName", fName);
                replyIntent.putExtra("employeeLName", lName);
                replyIntent.putExtra("employeeEmail", email);
                replyIntent.putExtra("employeePhone", phone);
                replyIntent.putExtra("employeeUsername", username);
                replyIntent.putExtra("employeePassword", password);
                replyIntent.putExtra("employeeDepartmentId", departmentId);
                replyIntent.putExtra("employeeAdmin", mAdmin);

                Employee newEmployee = new Employee(employeeId, fName, lName, email, phone, departmentId, mAdmin, username, password);
                mEmployeeViewModel.insert(newEmployee);

                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            parent.getItemAtPosition(pos);
        }
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radAdmin:
                if (checked)
                    mAdmin = 1;
                break;
            case R.id.radEmp:
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
