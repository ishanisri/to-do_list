package com.example.ishanisrivastava.to_dolistapp;

public class ToDoList {
    private int _id;
    private String _task;
    public ToDoList(){

    }

    public ToDoList(String task){
        this._task=task;

}

public void set_id(int id){
    this._id=id;
    }
    public void set_task(String task){
        this._task=task;
    }

    public String get_task(){
        return _task;
    }
}
