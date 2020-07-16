package com.suraj.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText mEtMail;
    private EditText mEtPassword;

    private Button mBtnSubmit;

    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      mEtMail = findViewById(R.id.et_mail);
        mEtPassword = findViewById(R.id.et_password);
        mBtnSubmit = findViewById(R.id.btn_submit);

        prefManager = getApplicationContext().getSharedPreferences("Register", MODE_PRIVATE);
        editor = prefManager.edit();


        final String userMail = prefManager.getString("MAIL", "");
        final String userPassword = prefManager.getString("PASSWORD", "");

        boolean isUserLoggedIn = prefManager.getBoolean("ISLOGGEDIN", false);

        if (isUserLoggedIn) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    moveToEndScreen();
                }
            }, 1000);


        }

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = mEtMail.getText().toString();
                String password = mEtPassword.getText().toString();
                if((mail.equals(userMail)) && (password.equals(userPassword)) )
                {
                    editor.putBoolean("ISLOGGEDIN",true);
                    editor.apply();
                    moveToEndScreen();

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Wrong Value are Passed", Toast.LENGTH_LONG).show();

                }

            }
        });


    }

        public void onCreateAccountClicked(View view)
        {
            Intent registerintent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(registerintent);
            finish();
        }
    private void moveToEndScreen() {

        startActivity(new Intent(LoginActivity.this, EndActivity.class));
        finish();

    }
}