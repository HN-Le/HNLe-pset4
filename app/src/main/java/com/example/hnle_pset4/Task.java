package com.example.hnle_pset4;

import java.io.Serializable;

public class Task implements Serializable {
    private String task_name;
    private int task_status;
    private int task_id;

    // Constructor for Tasks from database
    public  Task (String task, int status){
        task_name = task;
        task_status = status;
    }

    public Task (String task, int status, int id_task){
        task_name = task;
        task_status = status;
        task_id = id_task;
    }

    // Get Task name
    public String getTask_name(){

        return task_name;
    }

    // Get Task status
    public int getTask_status() {
        return task_status;
    }

    // Get Task ID
    public int getTask_id() {

        return task_id;
    }

    // Set Task name
    public void setTask_name(String newTask) {

        task_name = newTask;
    }

    // Set Task status
    public void setTask_status (int newStatus) {

        task_status = newStatus;

    }

    // Set Task ID
    public void setTask_id(int id_task) {

        task_id = id_task;
    }


}
