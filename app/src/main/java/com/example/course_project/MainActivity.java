package com.example.course_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText editTextTask;
    private DatePicker datePickerTask;
    private TimePicker timePickerTask;
    private Button btnAddTask;
    private ListView lvTasks;
    private DatabaseHelper databaseHelper;
    private ArrayAdapter<String> taskAdapter;
    private ArrayList<String> tasks = new ArrayList<>();
    private ArrayList<Task> task_ = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        datePickerTask = findViewById(R.id.datePickerTask);
        timePickerTask = findViewById(R.id.timePickerTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        lvTasks = findViewById(R.id.lvTasks);
        databaseHelper = new DatabaseHelper(this);

        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        lvTasks.setAdapter(taskAdapter);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = editTextTask.getText().toString().trim();
                String task = editTextTask.getText().toString().trim();
                int year = datePickerTask.getYear();
                int month = datePickerTask.getMonth();
                int day = datePickerTask.getDayOfMonth();
                int hour = timePickerTask.getHour();
                int minute = timePickerTask.getMinute();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                Date date = new Date(year - 1900, month, day, hour, minute);
                String formattedDate = dateFormat.format(date);
                String deadline = dateFormat.format(date);
                if (!task.isEmpty()){
                    Task task1 = new Task();
                    task1.setName(taskName);
                    task1.setDeadline(deadline);
                    databaseHelper.addTask(task1);
                    task_.add(task1);
                    tasks.add(task + "-" + formattedDate);
                    taskAdapter.notifyDataSetChanged();
                    editTextTask.setText("");
                }
            }
        });
        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String task = taskAdapter.getItem(position);
                Toast.makeText(MainActivity.this, task, Toast.LENGTH_SHORT).show();
            }
        });
        lvTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tasks.remove(position);
                taskAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
    private void loadTasksFromDatabase(){
        tasks.clear();
        //tasks.addAll(databaseHelper.getAllTask());
        taskAdapter.notifyDataSetChanged();
    }
    private void deleteTaskFromDatabase(int taskId){
        databaseHelper.deleteTask(taskId);
        loadTasksFromDatabase();
    }
}