package com.example.course_project.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.course_project.App;
import com.example.course_project.Model.Task;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Task>> taskLiveData = App.getInstance().getTaskDao().getAllLiveData();

    public LiveData<List<Task>> getTaskLiveData(){
        return taskLiveData;
    }
}
