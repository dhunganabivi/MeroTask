package com.example.merotask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private Button loginButton;
    private FirebaseAuth mAuth;
    EditText username,password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        register=(TextView)findViewById(R.id.mainRegister_id);
        register.setOnClickListener(this);

        username=(EditText)findViewById(R.id.userName_id);
        password=(EditText)findViewById(R.id.password_id);

        loginButton=(Button)findViewById(R.id.button1_id);
        loginButton.setOnClickListener(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,RegisterUser.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {
       String email=username.getText().toString().trim();
       String pass=password.getText().toString().trim();


       if (TextUtils.isEmpty(email)){
           Toast.makeText(this,"Please fill your email", Toast.LENGTH_SHORT).show();
       }

        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please fill your password", Toast.LENGTH_SHORT).show();
        }

        else {
            loginUser(email,pass);
        }

    }

    private void loginUser(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,WelcomeActivity.class);
                            startActivity(i);

                        } else {
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }

}