package com.example.loginformandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.loginformandroid.databinding.ActivityLoginFormAndroidBinding;

public class LoginFormAndroid extends AppCompatActivity {

    ActivityLoginFormAndroidBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginFormAndroidBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login_form_android);

        databaseHelper = new DatabaseHelper(this);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                if (email.equals("") || password.equals(""))
                    Toast.makeText(LoginFormAndroid.this, "All fields are obligatory!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkCredentials = databaseHelper.checkEmailPass(email, password);

                    if (checkCredentials == true) {
                        Toast.makeText(LoginFormAndroid.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }   else {
                        Toast.makeText(LoginFormAndroid.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.SignupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginFormAndroid.this, SignupFormAndroid.class);
                startActivity(intent);
            }
        });
    }
}