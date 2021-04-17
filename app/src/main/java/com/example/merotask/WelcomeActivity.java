package com.example.merotask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private RecyclerView tasksRecyclerView;
    private MyAdapter toDoAdapter;
    private List<taskViewModelClass> taskList;
    private FloatingActionButton floating;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseUser users;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        taskList=new ArrayList<>();

        mAuth= FirebaseAuth.getInstance();
        users= mAuth.getCurrentUser();
        userId=users.getUid();
        mRef = FirebaseDatabase.getInstance().getReference().child("Hello world").child(userId);


        floating=findViewById(R.id.floating_id);//definig floating button
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        tasksRecyclerView=findViewById(R.id.taskView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        toDoAdapter=new MyAdapter(this);
        tasksRecyclerView.setAdapter(toDoAdapter);

       taskViewModelClass task=new taskViewModelClass();

        task.setTask("this is a test task");
        task.setStatus(0);

        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        toDoAdapter.setTask(taskList);

    }

    private void addTask() {
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        LayoutInflater inflator= LayoutInflater.from(this);

        View view=inflator.inflate(R.layout.savetask_layout, null);
        dialog.setView(view);

        final AlertDialog alertdialog= dialog.create();
        alertdialog.setCancelable(false);

        final EditText save=view.findViewById(R.id.Enter);
        Button ok= view.findViewById(R.id.ok_btn);
        Button cancel= view.findViewById(R.id.cancel_btn);

        cancel.setOnClickListener(v -> {alertdialog.dismiss();});

        ok.setOnClickListener(v -> {
            String Id=mRef.push().getKey();
            String meroTask=save.getText().toString().trim();



                    if (TextUtils.isEmpty(meroTask)){
                        save.setError("please add task");
                        return;
                    } else {
                        taskViewModelClass mtask=new taskViewModelClass();
                        mRef.child(Id).setValue(mtask).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                    }
                    alertdialog.dismiss();
                }
        );
        alertdialog.show();



    }
    }
