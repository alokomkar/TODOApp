package com.letsventure.todo.model;

/**
 * Created by Alok Omkar on 2016-11-26.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ToDoModel {

    public static final String PENDING = "PENDING";
    public static final String COMPLETED = "COMPLETED";
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("scheduledDate")
    @Expose
    private String scheduledDate;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The scheduledDate
     */
    public String getScheduledDate() {
        return scheduledDate;
    }

    /**
     *
     * @param scheduledDate
     * The scheduledDate
     */
    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TodoModel : " + id + " : status : " +status + " : Schedule Date  : " + scheduledDate + " : description : " + description;
    }
}
