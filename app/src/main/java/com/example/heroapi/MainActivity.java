package com.example.heroapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login=findViewById(R.id.loginButton);
        Button register=findViewById(R.id.register);
        final EditText username=findViewById(R.id.usernametext);
        final EditText password=findViewById(R.id.passwordtext);

        final CheckBox rememberme = (CheckBox) findViewById(R.id.rememberMe);
        final User user=new User(MainActivity.this);
        rememberme.setChecked(user.isRememberedForLogin());

        if (rememberme.isChecked()){
            username.setText(user.getUsernameForLogin(),TextView.BufferType.EDITABLE);
            password.setText(user.getPasswordForLogin(),TextView.BufferType.EDITABLE);
        } else {
            username.setText("",TextView.BufferType.EDITABLE);
            password.setText("", TextView.BufferType.EDITABLE);
        }
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToRegActivity = new Intent(MainActivity.this, RegActivity.class);
                startActivity(goToRegActivity);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.isValidUsername(username.getText().toString())
                        && Validation.isValidPassword(password.getText().toString())){
                    user.setUsernameForLogin(username.getText().toString());
                    user.setPasswordForLogin(password.getText().toString());
                    if (rememberme.isChecked()){
                        user.setRemembermeKeyForLogin(true);
                    } else {
                        user.setRemembermeKeyForLogin(false);
                    }
                    Intent goToSeachActivity=new Intent(MainActivity.this, SearchActivity.class);
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
