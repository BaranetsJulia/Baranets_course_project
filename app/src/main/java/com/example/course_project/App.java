package com.example.course_project;

import android.app.Application;

import androidx.room.Room;

import com.example.course_project.data.DatabaseHelper;
import com.example.course_project.data.TaskDao;

public class App extends Application {

    private DatabaseHelper database;
    private TaskDao taskDao;
    private static App instance;
    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                        DatabaseHelper.class, "task.db")
                .allowMainThreadQueries()
                .build();
        taskDao = database.taskDao();
    }

    public DatabaseHelper getDatabase(){
        return database;
    }

    public void setDatabase(DatabaseHelper database){
        this.database = database;
    }

    public TaskDao getTaskDao(){
        return taskDao;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
}

