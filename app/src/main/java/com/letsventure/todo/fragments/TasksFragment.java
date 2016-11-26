package com.letsventure.todo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.letsventure.todo.R;
import com.letsventure.todo.adapter.TasksRecyclerAdapter;
import com.letsventure.todo.model.ToDoModel;
import com.letsventure.todo.presenter.ToDoPresenter;
import com.letsventure.todo.view.ToDoViewInteractor;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;

/**
 * Created by Alok Omkar on 2016-11-26.
 */

public class TasksFragment extends Fragment implements ToDoViewInteractor, TasksRecyclerAdapter.TasksRecyclerrAdapterListener {

    @Bind(R.id.todoRecyclerView)
    RecyclerView todoRecyclerView;

    private ToDoPresenter toDoPresenter;
    private int position;
    private TasksRecyclerAdapter tasksRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_todo_tasks_layout, container, false);
        ButterKnife.bind(this, rootView);
        if( toDoPresenter == null ) {
            toDoPresenter = new ToDoPresenter(this);
        }
        toDoPresenter.getTasks(position == 0);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccess(List<ToDoModel> toDoModel) {
        if( toDoModel != null ) {
            Log.d("TasksFragment", toDoModel.toString());
            setupRecyclerView(toDoModel);
        }

    }

    private void setupRecyclerView(List<ToDoModel> toDoModel) {
        if( tasksRecyclerAdapter == null ) {
            tasksRecyclerAdapter = new TasksRecyclerAdapter(toDoModel, this);
            todoRecyclerView.setLayoutManager( new LinearLayoutManager(getContext()) );
            todoRecyclerView.setAdapter(tasksRecyclerAdapter);
        }
        else {
            tasksRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(RetrofitError error) {
        error.printStackTrace();
        Snackbar.make(getActivity().findViewById(android.R.id.content),
                R.string.unable_to_fetch_tasks,
                Snackbar.LENGTH_LONG ).show();
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onResetAdapter() {
        tasksRecyclerAdapter = new TasksRecyclerAdapter(toDoModel, this);
        todoRecyclerView.setLayoutManager( new LinearLayoutManager(getContext()) );
        todoRecyclerView.setAdapter(tasksRecyclerAdapter);
    }
}
