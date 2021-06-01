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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText emailTextRegister,nameTextRegister,passwordTextRegister;
    Button register;
    String password;
    TextView login;
    User user;
    private FirebaseAuth registerAuth;
    private DatabaseReference ref;
    private FirebaseDatabase database;
    private String userUid;
    private int score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
		doRegister();
            }
        });
    }
	private void doRegister() {
        registerAuth=FirebaseAuth.getInstance();
       String email= emailTextRegister.getText().toString();
        password = passwordTextRegister.getText().toString();
       String name = nameTextRegister.getText().toString();
       if (!(email.isEmpty())&&!(password.isEmpty())&&!(name.isEmpty())){
           registerAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()){
                       System.out.println("Kullanıcı oluşturuldu..");
                        Toast.makeText(RegisterActivity.this,"Kullanıcı oluşturuldu...",Toast.LENGTH_LONG).show();
                        saveUserDatabase();
                       Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                       startActivity(intent);
                       finish();
                   }else{
                       System.out.println("Kullanıcı oluşturulamadı..");
                       Toast.makeText(RegisterActivity.this,"Kullanıcı oluşturulamadı...",Toast.LENGTH_LONG).show();
                   }
               }
           });
           }
       }
	private void saveUserDatabase() {
        userUid = registerAuth.getUid();
        database=FirebaseDatabase.getInstance();
        ref=database.getReference();
        if (userUid!=null){
             user = new User(emailTextRegister.getText().toString(),nameTextRegister.getText().toString(),password,score);
            ref.child("users").child(userUid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        System.out.println("Kullanıcı Database kaydedildi..");}
                    else{
                        System.out.println("error...");
                    }
                }
            });
        }else{
            System.out.println("Userid has no useruid... ");
        }
    }
	public String userReturn(){
        return  userUid;
    }
    private void initView() {
        emailTextRegister= ((EditText) findViewById(R.id.email));
        nameTextRegister= ((EditText) findViewById(R.id.name));
        passwordTextRegister= ((EditText) findViewById(R.id.password));
        register= ((Button) findViewById(R.id.register));
        login= ((TextView) findViewById(R.id.login));
    }
}
