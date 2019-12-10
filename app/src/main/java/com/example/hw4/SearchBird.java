package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchBird extends AppCompatActivity implements View.OnClickListener {

    //Declare variables

    EditText editTextSearchZip;
    Button buttonSearch, buttonAddImportance;
    TextView textViewFindBird, textViewFindPerson, textViewFindImportance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bird);

        editTextSearchZip = findViewById(R.id.editTextZipSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonAddImportance = findViewById(R.id.buttonAddImportance);

        textViewFindBird = findViewById(R.id.textViewFindBird);
        textViewFindPerson = findViewById(R.id.textViewFindPerson);
        //textViewFindImportance = findViewById(R.id.textViewFindImportance);

        buttonSearch.setOnClickListener(this);
        buttonAddImportance.setOnClickListener(this);
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

        if (item.getItemId() == R.id.itemEnterBird) {
            Intent enterIntent = new Intent(this, MainActivity.class);
            startActivity(enterIntent);

        } else if (item.getItemId() == R.id.itemLogOut) {
                Intent logoutIntent = new Intent(this, LoginMain.class);
                startActivity(logoutIntent);

        } else if (item.getItemId() == R.id.ItemHighestImportance) {
            Intent logoutIntent = new Intent(this, HighestImportance.class);
            startActivity(logoutIntent);

        } else {
            Toast.makeText(this, "You are already on the search page, you fool!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        //Declare firebase variables

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("birds");

        //When search button is clicked, sort all listings by zip code, then create listener with most recent entry
        if (v == buttonSearch) {
            String findzip = editTextSearchZip.getText().toString();
            int StringInt = Integer.parseInt(findzip);

            if (StringInt <= 100000) {
                Toast.makeText(this, "Zip Code is Invalid", Toast.LENGTH_SHORT).show();
            } else if (StringInt >= 999999) {
                Toast.makeText(this, "Zip Code is Invalid", Toast.LENGTH_SHORT).show();
            }

            else
            myRef.orderByChild("Zip").equalTo(findzip).addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //create local variables based on the data in the firebase database

                    birds foundbird = dataSnapshot.getValue(birds.class);
                    String findbirdname = foundbird.birdName;
                    String findSpotter = foundbird.personSearching;
                    //String findimportance = foundbird.importance;

                    //Set the text fields to the variables from the database.

                    textViewFindBird.setText(findbirdname);
                    textViewFindPerson.setText(findSpotter);
                    //textViewFindImportance.setText(findimportance);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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


        if (v == buttonAddImportance) {
            String findzip = editTextSearchZip.getText().toString();
            myRef.orderByChild("Zip").equalTo(findzip).addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    birds foundbird = dataSnapshot.getValue(birds.class);
                    int findimportance = foundbird.importance;
                    String Changekey = dataSnapshot.getKey();
                    //int importanceInt = Integer.parseInt(findimportance);
                    int updatedImportance = findimportance + 1;
                    //String StringImportance = String.valueOf(updatedImportance);


                    myRef.child(Changekey).child("importance").setValue(updatedImportance);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
    }

}
