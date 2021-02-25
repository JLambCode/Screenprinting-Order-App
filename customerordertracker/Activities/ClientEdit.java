package com.example.customerordertracker.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Client;
import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.Entities.POC;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ClientViewModel;
import com.example.customerordertracker.ViewModel.OrderViewModel;
import com.example.customerordertracker.ViewModel.POCViewModel;
import com.example.customerordertracker.ui.OrderAdapter;
import com.example.customerordertracker.ui.POCAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.customerordertracker.Utilities.Constants.NEW_POC_ACTIVITY_REQUEST_CODE;

public class ClientEdit extends AppCompatActivity {

    private ClientViewModel mClientViewModel;
    private POCViewModel mPocViewModel;
    private EditText mName;
    private EditText mAddress;
    private EditText mCity;
    private EditText mZip;
    private EditText mPhone;
    private int clientId;
    private int numPocs;
    private Spinner mState;
    private List<POC> PoCData = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_edit);
        setTitle("Edit Client");
        mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        mPocViewModel = new ViewModelProvider(this).get(POCViewModel.class);
        try {
            RecyclerView recyclerView = findViewById(R.id.poc_list_recycler);
            final POCAdapter adapter = new POCAdapter(PoCData, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            mPocViewModel.getAllPOC().observe(this, poc ->{
                List<POC> clientPOCs = new ArrayList<>();
                for (POC p:poc){
                    if (p.getClientID()==clientId){
                        clientPOCs.add(p);
                    }
                }
                adapter.setPOCs(clientPOCs);
                numPocs = clientPOCs.size();
            });
        }
        catch (Exception e) {
            e.printStackTrace();
            numPocs = 0;
        }


        mName = findViewById(R.id.inputName);
        mAddress = findViewById(R.id.inputAddress);
        mCity = findViewById(R.id.inputCity);
        mZip = findViewById(R.id.inputZip);
        mPhone = findViewById(R.id.inputPhone);

        mPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        clientId = getIntent().getIntExtra("clientId", 0);

        mName.setText(getIntent().getStringExtra("clientName"));
        mAddress.setText(getIntent().getStringExtra("clientAddress"));
        mCity.setText(getIntent().getStringExtra("clientCity"));
        mZip.setText(getIntent().getStringExtra("clientZipCode"));
        mPhone.setText(getIntent().getStringExtra("clientPhone"));

        mState = findViewById(R.id.spinnerState);
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this,R.array.state_array, android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mState.setAdapter(spinAdapter);

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });



        FloatingActionButton save = findViewById(R.id.btnSave);
        save.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mName.getText()) ||
                    TextUtils.isEmpty(mAddress.getText()) ||
                    TextUtils.isEmpty(mPhone.getText()) ||
                    TextUtils.isEmpty(mCity.getText()) ||
                    TextUtils.isEmpty(mZip.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_SHORT).show();
            }
            else {
                String name = mName.getText().toString();
                String address = mAddress.getText().toString();
                String city = mCity.getText().toString();
                String state = mState.getSelectedItem().toString();
                String zip = mZip.getText().toString();
                String phone = mPhone.getText().toString();

                replyIntent.putExtra("clientName", name);
                replyIntent.putExtra("clientAddress", address);
                replyIntent.putExtra("clientCity", city);
                replyIntent.putExtra("clientState", state);
                replyIntent.putExtra("clientZipCode", zip);
                replyIntent.putExtra("clientPhone", phone);
                replyIntent.putExtra("clientId", clientId);

                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }

    private void updateList() {
        mPocViewModel = new ViewModelProvider(this).get(POCViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.poc_list_recycler);
        final POCAdapter adapter = new POCAdapter(PoCData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mPocViewModel.getAllPOC().observe(this, poc -> {
            List<POC> clientPOCs = new ArrayList<>();
            for (POC p:poc){
                if (p.getClientID()==clientId){
                    clientPOCs.add(p);
                }
            }
            adapter.setPOCs(clientPOCs);
            numPocs = clientPOCs.size();
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
