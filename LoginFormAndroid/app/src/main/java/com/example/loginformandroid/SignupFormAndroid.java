package com.example.loginformandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.loginformandroid.databinding.ActivitySignupFormAndroidBinding;

public class SignupFormAndroid extends AppCompatActivity {

    ActivitySignupFormAndroidBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupFormAndroidBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirm = binding.signupConfirm.getText().toString();

                if (email.equals("") || password.equals("") || confirm.equals(""))
                    Toast.makeText(SignupFormAndroid.this, "All fields are obligatory", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(confirm)) {
                        Boolean checkUserEmail = databaseHelper.checkMail(email);

                        if (checkUserEmail == false) {
                            Boolean insert = databaseHelper.insertData(email, password);

                            if (insert == true) {
                                Toast.makeText(SignupFormAndroid.this, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginFormAndroid.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupFormAndroid.this, "Sign up failed!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupFormAndroid.this, "This user already exist, you can log in!", Toast.LENGTH_SHORT).show();
                        }
                    }   else {
                        Toast.makeText(SignupFormAndroid.this, "Invalid password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.LoginRedirect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginFormAndroid.class);
                startActivity(intent);
            }
        });
    }
}