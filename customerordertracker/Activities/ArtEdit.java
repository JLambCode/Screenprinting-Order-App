package com.example.customerordertracker.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Art;
import com.example.customerordertracker.Entities.Item;
import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ArtViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.customerordertracker.Utilities.Constants.EDIT_ART_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.FIND_ART_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_ITEM_ACTIVITY_REQUEST_CODE;

public class ArtEdit extends AppCompatActivity {

    private ArtViewModel mArtViewModel;
    private EditText mName;
    private String mPlacement;
    private EditText mNumberOfColors;
    private EditText mArtLoc;
    private Spinner mPlacementSpin;
    private int orderId;
    private int artId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_edit);
        setTitle("Art Info");

        mName = findViewById(R.id.inputName);
        mNumberOfColors = findViewById(R.id.inputNumberColors);
        mArtLoc = findViewById(R.id.inputArtFile);

        mPlacementSpin = findViewById(R.id.spinnerPlacement);
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this,R.array.placement_array, android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPlacementSpin.setAdapter(spinAdapter);

        orderId = getIntent().getIntExtra("orderId", 0);
        artId = getIntent().getIntExtra("artId", 0);
        mName.setText(getIntent().getStringExtra("artName"));
        mPlacement = mPlacementSpin.getSelectedItem().toString();
        mNumberOfColors.setText(getIntent().getStringExtra("artNumberColors"));
        mArtLoc.setText(getIntent().getStringExtra("artLocation"));

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        Button browse = findViewById(R.id.btnBrowse);
        browse.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/jpeg");
            startActivityForResult(intent, FIND_ART_ACTIVITY_REQUEST_CODE);
        });

        FloatingActionButton save = findViewById(R.id.btnSave);
        save.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mName.getText()) ||
                    TextUtils.isEmpty(mNumberOfColors.getText())) {
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_LONG).show();
            }
            else {
                replyIntent.putExtra("artId", artId);
                replyIntent.putExtra("artOrderId", orderId);
                replyIntent.putExtra("artName", mName.getText().toString());
                replyIntent.putExtra("artPlacement", mPlacement);
                replyIntent.putExtra("artNumberColors", mNumberOfColors.getText().toString());
                replyIntent.putExtra("artLocation", mArtLoc.getText().toString());
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

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if(requestCode == FIND_ART_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            try {
                Uri uri = null;
                if (replyIntent != null) {
                    uri = replyIntent.getData();
                    mArtLoc.setText(uri.getPath());
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"File not found!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
