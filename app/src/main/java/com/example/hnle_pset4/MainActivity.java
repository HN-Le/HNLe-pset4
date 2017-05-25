package com.example.hnle_pset4;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    Context context;
    Task task;
    ArrayList<Task> taskList;
    String task_name;

    TextView tasks;
    ListView lvitems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the database helper
        context = this;
        helper =  new DBHelper(this);

        lvitems = (ListView) findViewById(R.id.lvitems) ;
        tasks = (TextView) findViewById(R.id.tasks) ;

//
//
//        // Change the task and then update
//        task.getTask_name();
//        helper.update(task);
//
//        // Delete task from database
//        helper.delete(task);
//


        // Ask for a list of all tasks
        taskList = helper.read();

        for (Task task : taskList){
            Log.d("TAAAAG", task.toString());
        }

        makeListAdapter();
    }


    public void input(View view) {

        EditText user_input = (EditText) findViewById(R.id.user_input);
        task_name = user_input.getText().toString();


        // Create a new task to store in the database
        task = new Task(task_name, 0);

        Log.d("tag4", task.toString());

        // Create task in database
        helper.create(task);

        Log.d("tag4", task_name);
        Log.d("tag4", task.toString());


    }

    public void makeListAdapter(){

        lvitems = (ListView) findViewById(R.id.lvitems) ;
        tasks = (TextView) findViewById(R.id.tasks);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, taskList);

        assert lvitems != null;
        lvitems.setAdapter(arrayAdapter);

    }

}
