package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegister extends AppCompatActivity {
    EditText etUser, etPwd, etRepwd;
    Button btnRegister, btnGoToLogin;
    DBHelper dBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUser = findViewById(R.id.etUsername);
        etPwd = findViewById(R.id.etPassword);
        etRepwd = findViewById(R.id.etRePassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnGoToLogin = findViewById(R.id.btnGoToLogin);
        dBHelper = new DBHelper(this);
        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(intent);
            }
        }
        );
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, pwd, rePwd;
                user = etUser.getText().toString();
                pwd = etPwd.getText().toString();
                rePwd = etRepwd.getText().toString();

                // check whether it is empty
                if (user.isEmpty() || pwd.isEmpty() || rePwd.isEmpty()) {
                    Toast.makeText(ActivityRegister.this, "Please fill in all required fields", Toast.LENGTH_LONG).show();
                } else {
                    if (pwd.equals(rePwd)) {
                        // check if user exist
                        if (dBHelper.checkUsername(user)) {
                            Toast.makeText(ActivityRegister.this, "User Already Exists", Toast.LENGTH_LONG).show();
                            return;
                        }
                        // Proceed with registration
                        boolean registeredSuccess = dBHelper.insertData(user, pwd);
                        if (registeredSuccess) {
                            Toast.makeText(ActivityRegister.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ActivityRegister.this, "User Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(ActivityRegister.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
