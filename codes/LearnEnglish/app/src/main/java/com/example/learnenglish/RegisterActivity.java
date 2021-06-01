package com.example.learnenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    EditText emailTextRegister,nameTextRegister,passwordTextRegister;
    Button register;
    String password;
    TextView login;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void initView() {
        emailTextRegister= ((EditText) findViewById(R.id.email));
        nameTextRegister= ((EditText) findViewById(R.id.name));
        passwordTextRegister= ((EditText) findViewById(R.id.password));
        register= ((Button) findViewById(R.id.register));
        login= ((TextView) findViewById(R.id.login));
    }
}
