package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HighestImportance extends AppCompatActivity {

    TextView textViewHighestBird, textViewHighestBirdSpotter, textViewHighestBirdZip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_importance);

        textViewHighestBirdSpotter = findViewById(R.id.textViewHighBirdSpotter);;
        textViewHighestBird = findViewById(R.id.textViewHighBirdName);
        textViewHighestBirdZip = findViewById(R.id.textViewHighZip);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("birds");

        myRef.orderByChild("importance").limitToLast(1).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                birds foundbird = dataSnapshot.getValue(birds.class);
                String findbirdname = foundbird.birdName;
                String findSpotter = foundbird.personSearching;
                String findzip = foundbird.Zip;

                //Set the text fields to the variables from the database.

                textViewHighestBird.setText(findbirdname);
                textViewHighestBirdSpotter.setText(findSpotter);
                textViewHighestBirdZip.setText(findzip);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //create local variables based on the data in the firebase database

                birds foundbird = dataSnapshot.getValue(birds.class);
                String findbirdname = foundbird.birdName;
                String findSpotter = foundbird.personSearching;
                String findzip = foundbird.Zip;

                //Set the text fields to the variables from the database.

                textViewHighestBird.setText(findbirdname);
                textViewHighestBirdSpotter.setText(findSpotter);
                textViewHighestBirdZip.setText(findzip);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Give action to each menu items to switch between pages

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemEnterBird) {
            Intent enterIntent = new Intent(this, MainActivity.class);
            startActivity(enterIntent);

        } else if(item.getItemId() == R.id.itemSearchBird) {
                Intent searchIntent = new Intent(this, SearchBird.class);
                startActivity(searchIntent);

        } else if (item.getItemId() == R.id.itemLogOut) {
            Intent logoutIntent = new Intent(this, LoginMain.class);
            startActivity(logoutIntent);

        } else {
            Toast.makeText(this, "You are already on the search page, you fool!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
