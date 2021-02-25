package com.example.customerordertracker.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Client;
import com.example.customerordertracker.Entities.POC;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ClientViewModel;
import com.example.customerordertracker.ViewModel.OrderViewModel;
import com.example.customerordertracker.ViewModel.POCViewModel;
import com.example.customerordertracker.ui.ClientAdapter;
import com.example.customerordertracker.ui.OrderAdapter;
import com.example.customerordertracker.ui.POCAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.customerordertracker.Utilities.Constants.DELETE_CLIENT_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.EDIT_CLIENT_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_CLIENT_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_ORDER_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_POC_ACTIVITY_REQUEST_CODE;

public class ClientInfo extends AppCompatActivity {

    private ClientViewModel mClientViewModel;
    private POCViewModel mPoCViewModel;
    private OrderViewModel mOrderViewModel;
    private TextView mName;
    private TextView mAddress;
    private TextView mCity;
    private TextView mState;
    private TextView mZip;
    private TextView mPhone;
    private int clientId;
    private int numPoCs;
    private int employeeId;
    private List<Client> clientData = new ArrayList<>();
    private List<POC> PoCData = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info);
        mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        mPoCViewModel = new ViewModelProvider(this).get(POCViewModel.class);
        mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        setTitle("Client Info");
        mName = findViewById(R.id.outputName);
        mAddress = findViewById(R.id.outputAddress);
        mCity = findViewById(R.id.outputCity);
        mState = findViewById(R.id.outputState);
        mZip = findViewById(R.id.outputZipCode);
        mPhone = findViewById(R.id.outputPhone);
        clientId = getIntent().getIntExtra("clientId", 0);
        mName.setText(getIntent().getStringExtra("clientName"));
        mAddress.setText(getIntent().getStringExtra("clientAddress"));
        mCity.setText(getIntent().getStringExtra("clientCity"));
        mState.setText(getIntent().getStringExtra("clientState"));
        mZip.setText(getIntent().getStringExtra("clientZipCode"));
        mPhone.setText(getIntent().getStringExtra("clientPhone"));

        RecyclerView recyclerView = findViewById(R.id.poc_list_recycler);
        final POCAdapter pocAdapter = new POCAdapter(PoCData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(pocAdapter);

        try {
            mPoCViewModel.getAllPOC().observe(this, pocs -> {
                List<POC> clientPocList = new ArrayList<>();
                for (POC p : pocs) {
                    if (p.getClientID() == clientId) {
                        clientPocList.add(p);
                    }
                }
                pocAdapter.setPOCs(clientPocList);
                numPoCs = clientPocList.size();
            });
        } catch (Exception e) {
            numPoCs = 0;
        }

        Button btnPoC = findViewById(R.id.btnAddPOC);
        btnPoC.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewPoC.class);
            intent.putExtra("clientId", clientId);
            intent.putExtra("clientName", getIntent().getStringExtra("clientName"));
            startActivityForResult(intent, NEW_POC_ACTIVITY_REQUEST_CODE);
        });

        Button clientOrders = findViewById(R.id.btnClientOrders);
        clientOrders.setOnClickListener(view -> {
            Intent intent = new Intent(this, ClientOrdersList.class);
            intent.putExtra("clientId", clientId);
            intent.putExtra("clientName", mName.getText().toString());
            startActivity(intent);
        });

        FloatingActionButton delete = findViewById(R.id.btnDelete);
        delete.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            mClientViewModel.getAllClients().observe(this, client -> {
                for(Client p:client ){
                    if(p.getClientID() == clientId){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Confirmation");
                        builder.setMessage("Are you sure you want to delete this client?");
                        builder.setCancelable(true);
                        builder.setPositiveButton("Delete", (dialog, which) -> {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                            builder1.setTitle("Confirmation");
                            builder1.setMessage("This operation cannot be undone, are you still sure?");
                            builder1.setCancelable(true);
                            builder1.setPositiveButton("Delete", (dialog1, which1) -> {
                                mClientViewModel.delete(p);
                                Toast.makeText(getApplicationContext(), "Client deleted", Toast.LENGTH_SHORT).show();setResult(RESULT_OK, replyIntent);
                                finish();
                            });
                            builder1.setNegativeButton("Cancel", (dialog1, which1) -> dialog.dismiss());
                            AlertDialog dialog1 = builder1.create();
                            dialog1.show();
                        });
                        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            });
        });

        FloatingActionButton edit = findViewById(R.id.btnEdit);
        edit.setOnClickListener(view -> {
            Intent intent = new Intent(this, ClientEdit.class);
            String name = mName.getText().toString();
            String address = mAddress.getText().toString();
            String city = mCity.getText().toString();
            String state = mState.getText().toString();
            String zip = mZip.getText().toString();
            String phone = mPhone.getText().toString();
            int clientId = getIntent().getIntExtra("clientId", 0);
            intent.putExtra("clientId", clientId);
            intent.putExtra("clientName", name);
            intent.putExtra("clientAddress", address);
            intent.putExtra("clientCity", city);
            intent.putExtra("clientState", state);
            intent.putExtra("clientZipCode", zip);
            intent.putExtra("clientPhone", phone);

            startActivityForResult(intent, EDIT_CLIENT_ACTIVITY_REQUEST_CODE);
        });

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });
    }

    private void updateList() {
        final ClientAdapter clientAdapter = new ClientAdapter(clientData, this);
        clientAdapter.notifyDataSetChanged();
        mClientViewModel.getAllClients().observe(this, clients -> {clientAdapter.setClients(clients);});
        mPoCViewModel = new ViewModelProvider(this).get(POCViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.poc_list_recycler);
        final POCAdapter pocAdapter = new POCAdapter(PoCData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(pocAdapter);
        pocAdapter.notifyDataSetChanged();
        mPoCViewModel.getAllPOC().observe(this, pocs -> {
            List<POC> clientPocList = new ArrayList<>();
            for(POC p:pocs){
                if(p.getClientID()==clientId){
                    clientPocList.add(p);
                }
            }
            pocAdapter.setPOCs(clientPocList);
            numPoCs = clientPocList.size();
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if (requestCode == EDIT_CLIENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            int clientId = replyIntent.getIntExtra("clientId", 0);
            String name = replyIntent.getStringExtra("clientName");
            String address = replyIntent.getStringExtra("clientAddress");
            String city = replyIntent.getStringExtra("clientCity");
            String state = replyIntent.getStringExtra("clientState");
            String zip = replyIntent.getStringExtra("clientZipCode");
            String phone = replyIntent.getStringExtra("clientPhone");
            mName.setText(name);
            mAddress.setText(address);
            mCity.setText(city);
            mState.setText(state);
            mZip.setText(zip);
            mPhone.setText(phone);
            Client client = new Client(clientId, name, address, city, state, zip, phone);
            mClientViewModel.update(client);
            Toast.makeText(getApplicationContext(), "Client successfully updated", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == NEW_POC_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int pocId = replyIntent.getIntExtra("pocId", 0);
            String fName = replyIntent.getStringExtra("pocFName");
            String lName = replyIntent.getStringExtra("pocLName");
            String phone = replyIntent.getStringExtra("pocPhone");
            String email = replyIntent.getStringExtra("pocEmail");
            int clientId = replyIntent.getIntExtra("pocClientId", 0);
            POC newPoc = new POC(pocId, fName, lName, phone, email, clientId);
            mPoCViewModel.insert(newPoc);
            Toast.makeText(getApplicationContext(), "Contact successfully added", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
