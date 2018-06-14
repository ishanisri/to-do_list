package com.example.ishanisrivastava.to_dolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper{


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "databaseToDo.db";
    private static final String TABLE_NAME = "ToDoList";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASK = "_task";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int versions) {
        super(context, TABLE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + "" + TABLE_NAME + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TASK + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void addTask(ToDoList toDo) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK, toDo.get_task());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }



    public void deleteTask(int position){
        //String task1="'"+task+"'";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID+ " = " + (position+1)+  ";");


    }

    public ArrayList<String> databaseToDisplay(){
        ArrayList<String> list=new ArrayList();

        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("_task"))!=null){
                list.add(c.getString(c.getColumnIndex("_task")));



            }
        }
        db.close();
        return list;

    }




}
