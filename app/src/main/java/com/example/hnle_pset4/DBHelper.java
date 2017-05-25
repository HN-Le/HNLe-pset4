package com.example.hnle_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Static strings
    private static final String DATABASE_NAME = "TaskDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_ID = "task_id";
    private static final String KEY_NAME = "task_name";
    private static final String KEY_STATUS = "task_status";
    private static final String TABLE = "task_table";

    // Constructor
    public DBHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_DB = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME
                + " TEXT NOT NULL," + KEY_STATUS + " TEXT NOT NULL)";

        db.execSQL(CREATE_DB);
    }

    // Upgrade database when helper object is made and there is one already
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void create(Task task) {
        SQLiteDatabase db = getWritableDatabase();
        onUpgrade(db, 1, 1);
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getTask_name());
        values.put(KEY_STATUS, task.getTask_status());
        db.insert(TABLE, null, values);
        db.close();
    }

    public ArrayList<Task> read(){
        SQLiteDatabase db = getReadableDatabase();

        // List of custom objects to store data
        ArrayList<Task> tasks = new ArrayList<>();

        // Create query to give to the cursor
        String query = "SELECT " + KEY_ID + ", " + KEY_NAME + ", " + KEY_STATUS + " FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null);

        // Set cursor to the beginning of the database
        if (cursor.moveToFirst()) {
            do {
                // add id, done-status and to-do from current row to TodoList
                String task_name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                int task_status = cursor.getInt(cursor.getColumnIndex(KEY_STATUS));
                int task_id = cursor.getInt(cursor.getColumnIndex(KEY_ID));

                // Create contact object with the retrieved data
                Task task = new Task(task_name, task_status, task_id);
                tasks.add(task);

                Log.d("Tag2", task_name);
                Log.d("Tag2", String.valueOf(task_status));
                Log.d("Tag2", String.valueOf(task_id));

                for (Task iets : tasks){
                    Log.d("Tag4", iets.toString());
                }
            }

            // While there is still a next entry
            while (cursor.moveToNext());
        }

        // Close database and cursor
        cursor.close();
        db.close();


        return tasks;
    }

    public int update(Task task){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getTask_name());
        values.put(KEY_STATUS, task.getTask_status());

        // Return whether it has succeeded or not
        return db.update(TABLE, values, KEY_ID + " = ? ", new String[] { String.valueOf(task.getTask_id())});
    }

    public void delete(Task task){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, " " + KEY_ID + " = ? ", new String[] {String.valueOf(task.getTask_id())});
        db.close();
    }







}





