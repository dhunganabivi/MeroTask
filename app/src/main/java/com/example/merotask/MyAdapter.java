package com.example.merotask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<taskViewModelClass> toDoList;
    private WelcomeActivity activity;

    public MyAdapter(WelcomeActivity activity) {
        this.activity = activity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        taskViewModelClass items=toDoList.get(position);
        holder.task.setText(items.getTask());     /////////////changing in model class. after changing, logging button not working.
        holder.task.setChecked(toBoolean(items.getStatus())); // ask to teacher

    }

    private boolean toBoolean(int n){
        return n!=0;
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public void setTask(List<taskViewModelClass>toDoList){
        this.toDoList= toDoList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task= itemView.findViewById(R.id.checkbox_id);
        }
    }
}

