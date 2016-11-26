package com.letsventure.todo.presenter;

import com.letsventure.todo.ToDoTaskAPI;
import com.letsventure.todo.model.ToDoModel;
import com.letsventure.todo.service.ApiGenerator;
import com.letsventure.todo.view.ToDoViewInteractor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Alok Omkar on 2016-11-26.
 */

public class ToDoPresenter {

    private ToDoTaskAPI toDoTaskAPI;
    private ToDoViewInteractor toDoViewInteractor;

    public ToDoPresenter(ToDoViewInteractor toDoViewInteractor) {
        this.toDoViewInteractor = toDoViewInteractor;
        this.toDoTaskAPI = ApiGenerator.createService(ToDoTaskAPI.class);
    }

    private Comparator<ToDoModel> comparator = new Comparator<ToDoModel>() {
        @Override
        public int compare(ToDoModel leftModel, ToDoModel rightModel) {
            return leftModel.getScheduledDate().compareToIgnoreCase(rightModel.getScheduledDate());
        }
    };

    public void getTasks(final boolean isToday) {
        toDoTaskAPI.getTodoTasks(new Callback<List<ToDoModel>>() {
            @Override
            public void success(List<ToDoModel> toDoModel, Response response) {

                //Perform Sorting here
                Collections.sort(toDoModel, comparator);
                List<ToDoModel> pendingModels = new ArrayList<>();
                List<ToDoModel> completedModels = new ArrayList<>();
                for( ToDoModel toDoModel1 : toDoModel ) {
                    if( toDoModel1.getStatus().equals(ToDoModel.PENDING)) {
                        pendingModels.add(toDoModel1);
                    }
                    else {
                        completedModels.add(toDoModel1);
                    }
                }
                toDoModel = new ArrayList<>();
                toDoModel.addAll(pendingModels);
                toDoModel.addAll(completedModels);
                String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
                List<ToDoModel> toDayModels = new ArrayList<ToDoModel>();
                for( ToDoModel toDoModel1 : toDoModel ) {
                    if( isToday ) {
                        //Add only today's items
                        if( toDoModel1.getScheduledDate().contains(dateString) ) {
                            toDayModels.add(toDoModel1);
                        }

                    }
                    else {
                        //Add other day's items
                        if( !toDoModel1.getScheduledDate().contains(dateString) ) {
                            toDayModels.add(toDoModel1);
                        }
                    }
                }
                toDoViewInteractor.onSuccess(toDayModels);
            }

            @Override
            public void failure(RetrofitError error) {
                toDoViewInteractor.onFailure(error);
            }
        });
    }
}
