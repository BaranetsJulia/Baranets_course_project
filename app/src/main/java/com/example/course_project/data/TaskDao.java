package com.example.course_project.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.course_project.Model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM Task")
    List<Task> getAll();

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllLiveData();

    @Query("SELECT * FROM Task WHERE uid IN (:taskIds)")
    List<Task> loadAllByIds (int[] taskIds);

    @Query("SELECT * FROM Task WHERE uid = :uid LIMIT 1")
    Task findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task tasks);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);
}
