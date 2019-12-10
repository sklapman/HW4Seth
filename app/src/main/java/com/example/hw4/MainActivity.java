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

    //Declare variables

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

    //Insert Menu into activity

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Give action to each menu items to switch between pages

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemSearchBird) {
            Intent searchIntent = new Intent(this, SearchBird.class);
            startActivity(searchIntent);
        }

        else if (item.getItemId() == R.id.itemLogOut) {
            Intent logoutIntent = new Intent(this, LoginMain.class);
            startActivity(logoutIntent);

        } else if (item.getItemId() == R.id.ItemHighestImportance) {
            Intent logoutIntent = new Intent(this, HighestImportance.class);
            startActivity(logoutIntent);

        }

        else {
            Toast.makeText(this, "You are already on the bird record page, you fool!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        //Declare database variables

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("birds");

        if (v == buttonSubmit) {

            //if button is selected, then read the users texts and add them to local variables
            String submitBirdName = editTextBirdName.getText().toString();
            String submitZip = editTextZipCode.getText().toString();
            String submitPerson = editTextPersonSearching.getText().toString();

            //create birds variable from the individual variables.
            birds createbirds = new birds(submitBirdName,submitZip,submitPerson,0);

            //PUsh birds class to firebase
            myRef.push().setValue(createbirds);

            Toast.makeText(this, "Submission Complete", Toast.LENGTH_SHORT).show();

        }


    }
}
