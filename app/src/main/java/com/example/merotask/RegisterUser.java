package com.example.merotask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {
    EditText registerName,registerNumber,registerEmail,registerPass;
    Button register;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();

        registerName=(EditText)findViewById(R.id.Name_id);
        registerNumber=(EditText)findViewById(R.id.number_id);
        registerEmail=(EditText)findViewById(R.id.Email_id);
        registerPass=(EditText)findViewById(R.id.registerPass_id);
        register=(Button)findViewById(R.id.Register_id);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName=registerName.getText().toString().trim();
                String phone=registerNumber.getText().toString().trim();
                String Email=registerEmail.getText().toString().trim();
                String pasword=registerPass.getText().toString().trim();

                registerUser(fullName,phone,Email,pasword);
            }


        });
    }

    private void registerUser(String fullName, String phone, String email, String pasword) {
        mAuth.createUserWithEmailAndPassword(email, pasword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterUser.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent i= new Intent(RegisterUser.this,MainActivity.class);
                            startActivity(i);

                        } else {
                            Toast.makeText(RegisterUser.this, "Failed", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
}