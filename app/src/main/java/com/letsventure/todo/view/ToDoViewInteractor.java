package com.letsventure.todo.view;

import com.letsventure.todo.model.ToDoModel;

import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by Alok Omkar on 2016-11-26.
 */

public interface ToDoViewInteractor {
    void onSuccess(List<ToDoModel> toDoModel);
    void onFailure(RetrofitError error);
}
