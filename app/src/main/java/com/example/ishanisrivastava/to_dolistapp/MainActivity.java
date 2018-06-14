package com.example.ishanisrivastava.to_dolistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> tasks=new ArrayList();
    int p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textTask=findViewById(R.id.textTask);
        Button btnAdd=findViewById(R.id.btnAdd);
         final Button btnDelete =findViewById(R.id.btnDelete);
        final ListView tasksList=findViewById(R.id.tasksList);

       final  DBHandler dbhandler=new DBHandler(this,null,null,1);
       tasks=dbhandler.databaseToDisplay();



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tasks.add(textTask.getText().toString());

                ListAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, tasks);

                tasksList.setAdapter(adapter);
                ToDoList toDo=new ToDoList(textTask.getText().toString());



                dbhandler.addTask(toDo);
                textTask.setText("");
                Log.d("a","hello");



            }
        });


        tasksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 p=position;

                tasksList.setSelection(position);
                view.setSelected(true); }});

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        tasks.remove(p);

                        ListAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, tasks);

                        tasksList.setAdapter(adapter);

                       dbhandler.deleteTask(p);


                    }
                });
                //display value here








    }
}
