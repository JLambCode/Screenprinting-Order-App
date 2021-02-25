package com.example.customerordertracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.EmployeeViewModel;


public class MainActivity extends AppCompatActivity {

    private EmployeeViewModel mEmployeeViewModel;
    private EditText mUsername;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Customer Order Tracker");
        mEmployeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        mUsername = findViewById(R.id.inputUsername);
        mPassword = findViewById(R.id.inputPassword);
        Button login = findViewById(R.id.btnLogin);
        Button admin = findViewById(R.id.btnAdmin);

        login.setOnClickListener(view -> {
            mEmployeeViewModel.getAllEmployees().observe(this, employees -> {
                for (Employee p : employees) {
                    if (p.getUsername().equals(mUsername.getText().toString()) && p.getPassword().equals(mPassword.getText().toString())) {
                        Intent intent = new Intent(this, FirstMenu.class);
                        startActivity(intent);
                        break;
                    } else {
                        Toast.makeText(getApplicationContext(), "Username or Password is incorrect", Toast.LENGTH_LONG).show();
                    }
                }
            });
        });
        admin.setOnClickListener(view -> {
            mEmployeeViewModel.getAllEmployees().observe(this, employees -> {
                for (Employee p : employees) {
                    if (p.getUsername().equals(mUsername.getText().toString())){
                        if(p.getPassword().equals(mPassword.getText().toString())){
                            if(p.getAdmin()==1){
                                Intent intent = new Intent(this, Admin.class);
                                int empId = p.getEmpID();
                                intent.putExtra("employeeId", empId);
                                startActivity(intent);
                                break;
                            } else {
                                Toast.makeText(getApplicationContext(), "You do not have admin privileges", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Password is incorrect", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Username is incorrect", Toast.LENGTH_LONG).show();
                    }
                }
            });
        });
    }
}