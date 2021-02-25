package com.example.customerordertracker.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Department;
import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.DepartmentViewModel;
import com.example.customerordertracker.ViewModel.EmployeeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class EmployeeEdit extends AppCompatActivity {

    private EmployeeViewModel mEmployeeViewModel;
    private DepartmentViewModel mDepartmentViewModel;
    private EditText mFName;
    private EditText mLName;
    private EditText mEmail;
    private EditText mPhone;
    private EditText mUsername;
    private EditText mPassword;
    private int mAdmin;
    private RadioButton mRadAdmin;
    private RadioButton mRadEmp;
    private int employeeId;
    private int departmentId;
    private List<Department> departmentData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit);
        setTitle("Edit Employee");
        mEmployeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        mFName = findViewById(R.id.inputFNameEdit);
        mLName = findViewById(R.id.inputLNameEdit);
        mEmail = findViewById(R.id.inputEmailEdit);
        mPhone = findViewById(R.id.inputPhoneEdit);
        mUsername = findViewById(R.id.inputUsernameEdit);
        mPassword = findViewById(R.id.inputPasswordEdit);
        employeeId = getIntent().getIntExtra("employeeId", 0);
        mFName.setText(getIntent().getStringExtra("employeeFName"));
        mLName.setText(getIntent().getStringExtra("employeeLName"));
        mEmail.setText(getIntent().getStringExtra("employeeEmail"));
        mPhone.setText(getIntent().getStringExtra("employeePhone"));
        mUsername.setText(getIntent().getStringExtra("employeeUsername"));
        mAdmin = getIntent().getIntExtra("employeeAdmin",0);
        mRadAdmin = findViewById(R.id.radAdminEdit);
        mRadEmp = findViewById(R.id.radEmpEdit);

        mPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        if (mAdmin == 1){
           mRadAdmin.setChecked(true);
        } else {
            mRadEmp.setChecked(true);
        }

        Spinner mDepartment = findViewById(R.id.spinnerDeptEdit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.departments_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDepartment.setAdapter(adapter);

        mDepartment.setSelection(getIntent().getIntExtra("departmentId",1));

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
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_LONG).show();
            }
            else {
                String fName = mFName.getText().toString();
                String lName = mLName.getText().toString();
                String email = mEmail.getText().toString();
                String phone = mPhone.getText().toString();
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                int departmentId = mDepartment.getSelectedItemPosition()+1;
                int admin = mAdmin;

                replyIntent.putExtra("employeeId", employeeId);
                replyIntent.putExtra("employeeFName", fName);
                replyIntent.putExtra("employeeLName", lName);
                replyIntent.putExtra("employeeEmail", email);
                replyIntent.putExtra("employeePhone", phone);
                replyIntent.putExtra("employeeUsername", username);
                replyIntent.putExtra("employeePassword", password);
                replyIntent.putExtra("employeeDepartmentId", departmentId);
                replyIntent.putExtra("employeeAdmin", admin);

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
