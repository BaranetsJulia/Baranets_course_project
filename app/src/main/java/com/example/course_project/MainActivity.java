package com.example.course_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView myListView;
    private String [] names = {" ", " "}; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView=(ListView) findViewById(R.id.lv);
        myListView.setAdapter(new MyBaseAdapter());
    }
    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return names.length;
        }
        @Override
        public Object getItem(int position) {
            return names [position];
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView (int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this, R.layout.list_item, null);
            TextView mTextView=(TextView) view.findViewById(R.id.tv_list);
            mTextView.setText(names[position]);
            return view;
        }
    }
}