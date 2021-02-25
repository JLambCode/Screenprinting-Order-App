package com.example.customerordertracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.customerordertracker.Entities.Art;
import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ArtViewModel;
import com.example.customerordertracker.ViewModel.OrderViewModel;
import com.example.customerordertracker.ui.ArtAdapter;
import com.example.customerordertracker.ui.OrderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.customerordertracker.Utilities.Constants.NEW_ART_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_EMPLOYEE_ACTIVITY_REQUEST_CODE;

public class ArtList extends AppCompatActivity {

    private ArtViewModel mArtViewModel;
    private List<Art> artData = new ArrayList<>();
    private int orderId;

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_art_list);
        orderId = getIntent().getIntExtra("orderId", 0);
        mArtViewModel = new ViewModelProvider(this).get(ArtViewModel.class);
        setTitle("Art List for Order " + orderId);

        RecyclerView recyclerView = findViewById(R.id.art_list_recycler);
        final ArtAdapter adapter = new ArtAdapter(artData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mArtViewModel.getAllArt().observe(this, arts -> {
            List<Art> orderArt = new ArrayList<>();
            for(Art p:arts){
                if(p.getOrderID()==orderId){
                    orderArt.add(p);
                }
            }
            adapter.setArt(orderArt);
        });

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        FloatingActionButton add = findViewById(R.id.btnAdd);
        add.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewArt.class);
            intent.putExtra("orderId", orderId);
            startActivityForResult(intent, NEW_ART_ACTIVITY_REQUEST_CODE);
        });
    }

    private void updateList() {
        mArtViewModel = new ViewModelProvider(this).get(ArtViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.art_list_recycler);
        final ArtAdapter adapter = new ArtAdapter(artData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mArtViewModel.getAllArt().observe(this, arts -> {
            List<Art> orderArt = new ArrayList<>();
            for(Art p:arts) {
                if (p.getOrderID() == orderId) {
                    orderArt.add(p);
                }
            }
            adapter.setArt(orderArt);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if (requestCode == NEW_ART_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int artId = replyIntent.getIntExtra("artId", 0);
            String name = replyIntent.getStringExtra("artName");
            String placement = replyIntent.getStringExtra("artPlacement");
            String numberOfColors = replyIntent.getStringExtra("artNumberColors");
            String location = replyIntent.getStringExtra("artLocation");
            int orderId = replyIntent.getIntExtra("artOrderId", 0);
            Art newArt = new Art(artId, name, location, numberOfColors, placement, orderId);
            mArtViewModel.insert(newArt);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
