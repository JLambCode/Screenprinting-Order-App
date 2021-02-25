package com.example.customerordertracker.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import static com.example.customerordertracker.Utilities.Constants.FIND_ART_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_ITEM_ACTIVITY_REQUEST_CODE;

public class NewArt extends AppCompatActivity {

    private ArtViewModel mArtViewModel;
    private TextView mName;
    private Spinner mPlacement;
    private TextView mNumberOfColors;
    private TextView mArtLoc;
    private int orderId;
    private int artId;
    private int artCount;
    private List<Art> artData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_art);
        orderId = getIntent().getIntExtra("orderId", 0);
        setTitle("Add Art for Order " + orderId);
        mArtViewModel = new ViewModelProvider(this).get(ArtViewModel.class);
        final ArtAdapter artAdapter = new ArtAdapter(artData, this);
        mArtViewModel.getAllArt().observe(this, arts -> {
            List<Art> allArt = new ArrayList<>();
            for (Art p : arts) {
                allArt.add(p);
            }
            artAdapter.setArt(allArt);
            artCount = allArt.size();
        });
        mName = findViewById(R.id.inputName);
        mNumberOfColors = findViewById(R.id.inputNumberColors);
        mArtLoc = findViewById(R.id.inputArtFile);
        mPlacement = findViewById(R.id.spinnerPlacement);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.placement_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPlacement.setAdapter(adapter);

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        Button browse = findViewById(R.id.btnBrowse);
        browse.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/jpg");
            startActivityForResult(intent, FIND_ART_ACTIVITY_REQUEST_CODE);
        });

        FloatingActionButton save = findViewById(R.id.btnSave);
        save.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(mName.getText()) ||
                    TextUtils.isEmpty(mName.getText()) ||
                    TextUtils.isEmpty(mNumberOfColors.getText())) {
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_SHORT).show();
            }
            else {

                replyIntent.putExtra("artId", artCount + 1);
                replyIntent.putExtra("artOrderId", orderId);
                replyIntent.putExtra("artName", mName.getText().toString());
                replyIntent.putExtra("artPlacement", mPlacement.getSelectedItem().toString());
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
