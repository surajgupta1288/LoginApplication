package com.suraj.loginapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EndActivity extends AppCompatActivity {
    private TextView mTvFirstName, mTvName,mTvRegd,mTvMail;
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Toolbar toolbar = findViewById(R.id.tl_end);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowTitleEnabled(false);//for not showing the title.

           // String userName = prefManager.getString("NAME", "");

         //   getSupportActionBar().setTitle(userName);
        }


        mTvFirstName = findViewById(R.id.tv_first_name);
        mTvName = findViewById(R.id.tv_name);
        mTvRegd = findViewById(R.id.tv_regd);
        mTvMail = findViewById(R.id.tv_mail);
        Button btnLogout = findViewById(R.id.btn_logout);


        prefManager = getApplicationContext().getSharedPreferences("Register", MODE_PRIVATE);
        editor = prefManager.edit();

        String userName = prefManager.getString("NAME", "");
        String userMail = prefManager.getString("MAIL", "");
        String userRegd = prefManager.getString("REGD","");

        mTvFirstName.setText(userName);
        mTvName.setText(userName);
        mTvRegd.setText(userRegd);
        mTvMail.setText(userMail);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(EndActivity.this).setTitle("Alert")
                        .setMessage("Are you sure you want to Logout")
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editor.putBoolean("ISLOGGEDIN", false);
                                editor.putString("NAME", "");
                                editor.putString("MAIL", "");
                                editor.putString("REGD", "");
                                editor.apply();

                                startActivity(new Intent(EndActivity.this, LoginActivity.class));
                                finish();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_end,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       // if (item.getItemId() == android.R.id.home){
      //      onBackPressed();
     //   }
        switch (item.getItemId()){

            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_logout:
                new AlertDialog.Builder(EndActivity.this).setTitle("Alert")
                        .setMessage("Are you sure you want to Logout")
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editor.putBoolean("ISLOGGEDIN", false);
                                editor.putString("NAME", "");
                                editor.putString("MAIL", "");
                                editor.putString("REGD", "");
                                editor.apply();

                                startActivity(new Intent(EndActivity.this, LoginActivity.class));
                                finish();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
                break;
        }
        return true;
    }
}
