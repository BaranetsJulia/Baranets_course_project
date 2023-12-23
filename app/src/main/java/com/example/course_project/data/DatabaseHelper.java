package com.example.course_project.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.course_project.Model.Task;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract TaskDao taskDao();
}
