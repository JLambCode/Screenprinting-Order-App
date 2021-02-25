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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Client;
import com.example.customerordertracker.Entities.POC;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ClientViewModel;
import com.example.customerordertracker.ui.ClientAdapter;
import com.example.customerordertracker.ui.OrderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NewClient extends AppCompatActivity {

    private ClientViewModel mClientViewModel;
    private EditText mName;
    private EditText mAddress;
    private EditText mCity;
    private EditText mZip;
    private EditText mPhone;
    private int clientCount;
    private Spinner mState;
    private List<Client> clientData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        setTitle("New Client");
        mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        final ClientAdapter adapter = new ClientAdapter(clientData, this);
        mClientViewModel.getAllClients().observe(this, clients -> {
            List<Client> allClients = new ArrayList<>();
            for (Client p : clients) {
                allClients.add(p);
            }
            adapter.setClients(allClients);
            clientCount = allClients.size();
        });
        mName = findViewById(R.id.inputName);
        mAddress = findViewById(R.id.inputAddress);
        mCity = findViewById(R.id.inputCity);
        mZip = findViewById(R.id.inputZip);
        mPhone = findViewById(R.id.inputPhone);

        mPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        mState = findViewById(R.id.spinState);
        ArrayAdapter<CharSequence> spinSizeAdapter = ArrayAdapter.createFromResource(this,R.array.state_array, android.R.layout.simple_spinner_item);
        spinSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mState.setAdapter(spinSizeAdapter);

        FloatingActionButton save = findViewById(R.id.btnSave);
        save.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(mName.getText()) ||
                    TextUtils.isEmpty(mAddress.getText()) ||
                    TextUtils.isEmpty(mCity.getText()) ||
                    TextUtils.isEmpty(mZip.getText()) ||
                    TextUtils.isEmpty(mPhone.getText())) {
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_SHORT).show();
            }
            else {
                String name = mName.getText().toString();
                String address = mAddress.getText().toString();
                String city = mCity.getText().toString();
                String state = mState.getSelectedItem().toString();
                String zip = mZip.getText().toString();
                String phone = mPhone.getText().toString();

                replyIntent.putExtra("clientId", ++clientCount);
                replyIntent.putExtra("clientName", name);
                replyIntent.putExtra("clientAddress", address);
                replyIntent.putExtra("clientCity", city);
                replyIntent.putExtra("clientState", state);
                replyIntent.putExtra("clientZip", zip);
                replyIntent.putExtra("clientPhone", phone);

                Toast.makeText(getApplicationContext(), "Client successfully added", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, replyIntent);
                finish();
            }

        });

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            parent.getItemAtPosition(pos);
        }
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
