package com.example.customerordertracker.Activities;

import android.os.Bundle;

import com.example.customerordertracker.Entities.Client;
import com.example.customerordertracker.Entities.Order;
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

public class SearchListClient extends AppCompatActivity {

    private ClientViewModel mClientViewModel;
    private POCViewModel mPOCViewModel;
    private List<Client> clientData;
    private List<POC> pocData;
    private int position;
    private CharSequence searchInput;

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        mPOCViewModel = new ViewModelProvider(this).get(POCViewModel.class);
        final POCAdapter pocAdapter = new POCAdapter(pocData, this);
        setTitle("Client Search Result");
        searchInput = getIntent().getCharSequenceExtra("searchInput");

        RecyclerView recyclerView = findViewById(R.id.search_list_recycler);
        final ClientAdapter adapter = new ClientAdapter(clientData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mClientViewModel.getAllClients().observe(this, clients -> {
            List<Client> searchedClients = new ArrayList<>();
            for(Client p:clients){
                if(p.getName().contains(searchInput) ||
                        p.getAddress().contains(searchInput) ||
                        p.getCity().contains(searchInput) ||
                        p.getState().contains(searchInput) ||
                        p.getPhone().contains(searchInput)){
                    searchedClients.add(p);
                }
            }
            mPOCViewModel.getAllPOC().observe(this, pocs -> {
                List<POC> searchedPocs = new ArrayList<>();
                for(POC p: pocs){
                    if(p.getFName().contains(searchInput) ||
                            p.getLName().contains(searchInput) ||
                            p.getEmail().contains(searchInput) ||
                            p.getPhone().contains(searchInput)) {
                        searchedPocs.add(p);
                    }
                }
                pocAdapter.setPOCs(searchedPocs);
            });
            adapter.setClients(searchedClients);
        });

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });
    }

    private void updateList() {

        RecyclerView recyclerView = findViewById(R.id.search_list_recycler);
        final ClientAdapter adapter = new ClientAdapter(clientData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mClientViewModel.getAllClients().observe(this, clients -> {
            List<Client> searchedClients = new ArrayList<>();
            for (Client p : clients) {
                if (p.getName().contains(searchInput) ||
                        p.getAddress().contains(searchInput) ||
                        p.getCity().contains(searchInput) ||
                        p.getState().contains(searchInput) ||
                        p.getPhone().contains(searchInput)) {
                    searchedClients.add(p);
                }
            }
            adapter.setClients(searchedClients);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
