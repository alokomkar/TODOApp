package com.letsventure.todo;

import com.letsventure.todo.model.ToDoModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Alok Omkar on 2016-11-26.
 */

public interface ToDoTaskAPI {
    @GET("/5838359d11000035088fd2ef")
    void getTodoTasks(Callback<List<ToDoModel>> toDoModelCallback);
}
