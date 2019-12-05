package com.example.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginMain extends AppCompatActivity implements View.OnClickListener {

    Button buttonLogin, buttonRegister;
    EditText editTextEmail, editTextPassword;

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


    }

    @Override
    public void onClick(View v) {

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (v == buttonRegister){
            Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT).show();
        }

        else if (v == buttonLogin){
            Intent MainIntent = new Intent(this,MainActivity.class);
            startActivity(MainIntent);

        }


    }
}
