package com.example.customerordertracker.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.customerordertracker.Entities.Client;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ClientViewModel;
import com.example.customerordertracker.ui.ClientAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.customerordertracker.Utilities.Constants.NEW_CLIENT_ACTIVITY_REQUEST_CODE;

public class ClientList extends AppCompatActivity {

    private ClientViewModel mClientViewModel;
    private List<Client> clientData = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_client_list);
        mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        setTitle("Client List");
        RecyclerView recyclerView = findViewById(R.id.client_list_recycler);
        final ClientAdapter adapter = new ClientAdapter(clientData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mClientViewModel.getAllClients().observe(this, clients -> { adapter.setClients(clients);});

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        FloatingActionButton add = findViewById(R.id.btnAdd);
        add.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewClient.class);
            startActivityForResult(intent, NEW_CLIENT_ACTIVITY_REQUEST_CODE);
        });
    }

    private void updateList() {
        mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.client_list_recycler);
        final ClientAdapter adapter = new ClientAdapter(clientData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mClientViewModel.getAllClients().observe(this, clients -> adapter.setClients(clients));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if (requestCode == NEW_CLIENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int clientId = replyIntent.getIntExtra("clientId", 0);
            String name = replyIntent.getStringExtra("clientName");
            String address = replyIntent.getStringExtra("clientAddress");
            String city = replyIntent.getStringExtra("clientCity");
            String state = replyIntent.getStringExtra("clientState");
            String zip = replyIntent.getStringExtra("clientZip");
            String phone = replyIntent.getStringExtra("clientPhone");
            Client client = new Client(clientId, name, address, city, state, zip, phone);
            mClientViewModel.insert(client);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
