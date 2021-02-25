package com.example.customerordertracker.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customerordertracker.Entities.POC;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ClientViewModel;
import com.example.customerordertracker.ViewModel.POCViewModel;
import com.example.customerordertracker.ui.POCAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import static com.example.customerordertracker.Utilities.Constants.EDIT_POC_ACTIVITY_REQUEST_CODE;

public class PoCInfo extends AppCompatActivity {

    private POCViewModel mPOCViewModel;
    private ClientViewModel mClientViewModel;
    private TextView mFName;
    private TextView mLName;
    private TextView mPhone;
    private TextView mEmail;
    private int clientId;
    private int pocId;
    private List<POC> pocData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poc_info);
        setTitle("Point of Contact Info");
        mPOCViewModel = new ViewModelProvider(this).get(POCViewModel.class);
        mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        mFName = findViewById(R.id.outputFName);
        mLName = findViewById(R.id.outputLName);
        mPhone = findViewById(R.id.outputPhone);
        mEmail = findViewById(R.id.outputEmail);
        pocId = getIntent().getIntExtra("pocId",0);
        clientId = getIntent().getIntExtra("pocClientId",0);
        mFName.setText(getIntent().getStringExtra("pocFName"));
        mLName.setText(getIntent().getStringExtra("pocLName"));
        mPhone.setText(getIntent().getStringExtra("pocPhone"));
        mEmail.setText(getIntent().getStringExtra("pocEmail"));

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        FloatingActionButton delete = findViewById(R.id.btnDelete);
        delete.setOnClickListener(view -> {
            mPOCViewModel.getAllPOC().observe(this, pocs -> {
                for(POC p: pocs) {
                    if (p.getPocid() == pocId) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Confirmation");
                        builder.setMessage("Are you sure you want to delete this contact?");
                        builder.setCancelable(true);
                        builder.setPositiveButton("Delete", (dialog, which) -> {
                            mPOCViewModel.delete(p);
                            Toast.makeText(getApplicationContext(), "Contact deleted", Toast.LENGTH_LONG).show();
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
            Intent intent = new Intent(this, PoCEdit.class);

            String fName = mFName.getText().toString();
            String lName = mLName.getText().toString();
            String email = mEmail.getText().toString();
            String phone = mPhone.getText().toString();
            int clientId = getIntent().getIntExtra("pocClientId", 0);
            int PoCId = getIntent().getIntExtra("pocId", 0);

            intent.putExtra("pocFName", fName);
            intent.putExtra("pocLName", lName);
            intent.putExtra("pocEmail", email);
            intent.putExtra("pocPhone", phone);
            intent.putExtra("pocClientId", clientId);
            intent.putExtra("pocId", PoCId);

            startActivityForResult(intent, EDIT_POC_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if(requestCode == EDIT_POC_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            int pocId = replyIntent.getIntExtra("pocId", 0);
            String fName = replyIntent.getStringExtra("pocFName");
            String lName = replyIntent.getStringExtra("pocLName");
            String email = replyIntent.getStringExtra("pocPhone");
            String phone = replyIntent.getStringExtra("pocEmail");
            int pocClientId = replyIntent.getIntExtra("pocClientId", 0);
            mFName.setText(fName);
            mLName.setText(lName);
            mPhone.setText(phone);
            mEmail.setText(email);

            POC updatedPoc = new POC(pocId, fName, lName, email, phone, pocClientId);
            mPOCViewModel.update(updatedPoc);
            final POCAdapter adapter = new POCAdapter(pocData, this);
            adapter.notifyDataSetChanged();
            mPOCViewModel.getAllPOC().observe(this, pocs -> {adapter.setPOCs(pocs);});
            Toast.makeText(getApplicationContext(), "Point of Contact successfully updated", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
