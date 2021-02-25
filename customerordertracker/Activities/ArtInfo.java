package com.example.customerordertracker.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Art;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ArtViewModel;
import com.example.customerordertracker.ui.ArtAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import static com.example.customerordertracker.Utilities.Constants.EDIT_ART_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.EDIT_EMPLOYEE_ACTIVITY_REQUEST_CODE;

public class ArtInfo extends AppCompatActivity {

    private ArtViewModel mArtViewModel;
    private TextView mName;
    private TextView mPlacement;
    private TextView mNumberOfColors;
    private TextView mArtLoc;
    private int orderId;
    private int artId;
    private List<Art> artData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_info);
        setTitle("Art Info");
        mArtViewModel = new ViewModelProvider(this).get(ArtViewModel.class);

        mName = findViewById(R.id.outputName);
        mPlacement = findViewById(R.id.outputPlacement);
        mNumberOfColors = findViewById(R.id.outputNumberColors);
        mArtLoc = findViewById(R.id.outputArtLocation);

        orderId = getIntent().getIntExtra("orderId", 0);
        artId = getIntent().getIntExtra("artId", 0);
        mName.setText(getIntent().getStringExtra("artName"));
        mPlacement.setText(getIntent().getStringExtra("artPlacement"));
        mNumberOfColors.setText(getIntent().getStringExtra("artNumberColors"));
        mArtLoc.setText(getIntent().getStringExtra("artLocation"));

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        FloatingActionButton delete = findViewById(R.id.btnDelete);
        delete.setOnClickListener(view -> {
            mArtViewModel.getAllArt().observe(this, art -> {
                for(Art p:art){
                    if(p.getArtID() == artId){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Confirmation");
                        builder.setMessage("Are you sure you want to delete this art?");
                        builder.setCancelable(true);
                        builder.setPositiveButton("Delete", (dialog, which) -> {
                            mArtViewModel.delete(p);
                            Toast.makeText(getApplicationContext(), "Art deleted", Toast.LENGTH_LONG).show();
                            finish();
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
            Intent intent = new Intent(this, ArtEdit.class);
            intent.putExtra("artId", artId);
            intent.putExtra("orderId", orderId);
            intent.putExtra("artName", mName.getText().toString());
            intent.putExtra("artPlacement", mPlacement.getText().toString());
            intent.putExtra("artNumberColors", mNumberOfColors.getText().toString());
            intent.putExtra("artLocation", mArtLoc.getText().toString());
            startActivityForResult(intent, EDIT_ART_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if (requestCode == EDIT_ART_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            int artId = replyIntent.getIntExtra("artId", 0);
            String name = replyIntent.getStringExtra("artName");
            String location = replyIntent.getStringExtra("artLocation");
            String numberOfColors = replyIntent.getStringExtra("artNumberColors");
            String placement = replyIntent.getStringExtra("artPlacement");
            int orderId = replyIntent.getIntExtra("artOrderId", 0);

            mName.setText(name);
            mArtLoc.setText(location);
            mNumberOfColors.setText(numberOfColors);
            mPlacement.setText(placement);

            Art updatedArt = new Art(artId, name, location, numberOfColors, placement, orderId);
            mArtViewModel.update(updatedArt);
            Toast.makeText(getApplicationContext(), "Art successfully updated", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
