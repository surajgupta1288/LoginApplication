package com.suraj.loginapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEtName;
    private EditText mEtMail;
    private EditText mEtRegd;
    private EditText mEtPassword;

    private Button mBtnSubmit;

    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEtName = findViewById(R.id.et_name);
        mEtMail = findViewById(R.id.et_mail);
        mEtRegd = findViewById(R.id.et_regd);
        mEtPassword = findViewById(R.id.et_password);
        // For button......
        mBtnSubmit = findViewById(R.id.btn_submit);

        prefManager = getApplicationContext().getSharedPreferences("Register", MODE_PRIVATE);
        editor = prefManager.edit();

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mEtName.getText().toString();
                String mail = mEtMail.getText().toString();
                String regd = mEtRegd.getText().toString();
                String password = mEtPassword.getText().toString();

                editor.putString("NAME",name);
                editor.putString("MAIL",mail);
                editor.putString("REGD",regd);
                editor.putString("PASSWORD",password);

                editor.apply();//for ui threads.....

                moveToLoginScreen();

            }
        });
    }

    private void moveToLoginScreen() {

        Intent loginintent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(loginintent);
        finish();
    }
}