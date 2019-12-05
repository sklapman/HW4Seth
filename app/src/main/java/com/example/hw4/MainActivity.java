package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextBirdName, editTextZipCode, editTextPersonSearching;
    Button buttonSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextBirdName = findViewById(R.id.editTextEnterBirdName);
        editTextZipCode = findViewById(R.id.editTextEnterZip);
        editTextPersonSearching = findViewById(R.id.editTextSighterName);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemSearchBird) {
            Intent searchIntent = new Intent(this, SearchBird.class);
            startActivity(searchIntent);
        }

        else {
            Toast.makeText(this, "You are already on the bird record page, you fool!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("birds");

        if (v == buttonSubmit) {
            String submitBirdName = editTextBirdName.getText().toString();
            String submitZip = editTextZipCode.getText().toString();
            String submitPerson = editTextPersonSearching.getText().toString();
            Integer submitImportance = 0;

            birds createbirds = new birds(submitBirdName,submitZip,submitPerson,submitImportance);

            myRef.push().setValue(createbirds);

        }


    }
}
