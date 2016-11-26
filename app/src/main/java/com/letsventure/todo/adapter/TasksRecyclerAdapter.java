package com.letsventure.todo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.letsventure.todo.R;
import com.letsventure.todo.model.ToDoModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Alok Omkar on 2016-11-26.
 */

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TasksRecyclerAdapter.ViewHolder> {

    private List<ToDoModel> toDoModels;
    private String prevHeader = "";

    public interface TasksRecyclerrAdapterListener {
        void onResetAdapter();
    }
    private TasksRecyclerrAdapterListener tasksRecyclerrAdapterListener;

    public TasksRecyclerAdapter(List<ToDoModel> toDoModels, TasksRecyclerrAdapterListener tasksRecyclerrAdapterListener) {
        this.toDoModels = toDoModels;
        this.tasksRecyclerrAdapterListener = tasksRecyclerrAdapterListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ToDoModel toDoModel = toDoModels.get(position);
        holder.todoCheckBox.setText(toDoModel.getDescription());
        holder.dateTextView.setText(toDoModel.getScheduledDate());
        holder.headerTextView.setText(toDoModel.getStatus());
        holder.todoCheckBox.setChecked(toDoModel.getStatus().equals(ToDoModel.COMPLETED));
        holder.todoCheckBox.setEnabled(toDoModel.getStatus().equals(ToDoModel.PENDING));
        if( prevHeader.isEmpty() ) {
            prevHeader = toDoModel.getStatus();
            holder.headerTextView.setVisibility(View.VISIBLE);
        }
        else {
            if( prevHeader.equals(toDoModel.getStatus()) ) {
                holder.headerTextView.setVisibility(View.GONE);
            }
            else {
                prevHeader = toDoModel.getStatus();
                holder.headerTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return toDoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.headerTextView)
        TextView headerTextView;
        @Bind(R.id.todoCheckBox)
        CheckBox todoCheckBox;
        @Bind(R.id.dateTextView)
        TextView dateTextView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            todoCheckBox.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if( view instanceof CheckBox ) {
                boolean isChecked = ((CheckBox) view).isChecked();
                int position = getAdapterPosition();
                if( position != RecyclerView.NO_POSITION ) {
                    ToDoModel toDoModel = toDoModels.get(position);
                    toDoModel.setStatus( isChecked ? ToDoModel.COMPLETED : ToDoModel.PENDING );
                    tasksRecyclerrAdapterListener.onResetAdapter();
                }
            }

        }
    }

}
