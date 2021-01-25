package com.example.heroapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        Button register=findViewById(R.id.registerSubmit);
        final EditText username=findViewById(R.id.reg_username);
        final EditText password=findViewById(R.id.reg_password);
        final EditText email=findViewById(R.id.reg_email);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Validation.isValidUsername(username.getText().toString()))&& (Validation.isValidPassword(password.getText().toString()))
                        && Validation.isValidEmail(email.getText().toString())){
                    Intent goToSeachActivity=new Intent(RegActivity.this, SearchActivity.class);
                    startActivity(goToSeachActivity);
                }
                else{
                    username.setError(getResources().getString(R.string.login_invalid_username));
                    username.requestFocus();
                }

            }
        });

    }
}