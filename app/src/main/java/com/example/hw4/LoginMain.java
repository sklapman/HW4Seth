package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginMain extends AppCompatActivity implements View.OnClickListener {

    //Declare variables

    Button buttonLogin, buttonRegister;
    EditText editTextEmail, editTextPassword;

    //Declare instance of FirebaseAuth

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);


        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPW);

        buttonRegister.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {

        //Create string variables from information user entered into text boxes

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        //if Register button is selected, create a user in firebase

        if (v == buttonRegister) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginMain.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                // Sign in success, update UI with the signed-in user's information
                            } else {
                                Toast.makeText(LoginMain.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user.
                            }

                        }
                    });
        } else if (v == buttonLogin) {
            final Intent MainIntent = new Intent(this,MainActivity.class);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(MainIntent);
                            } else {
                                Toast.makeText(LoginMain.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user.
                            }

                            // ...
                        }
                    });
        }
    }

}
