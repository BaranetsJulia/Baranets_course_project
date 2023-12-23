package com.example.course_project.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "tasks")
    public String tasks;
    @ColumnInfo(name = "timestamp")
    public long timestamp;
    @ColumnInfo(name = "done")
    public boolean done;

    public Task(){
    }

    protected Task(Parcel in) {
        uid = in.readInt();
        tasks = in.readString();
        timestamp = in.readLong();
        done = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(tasks);
        dest.writeLong(timestamp);
        dest.writeByte((byte) (done ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (uid != task.uid) return false;
        if (timestamp != task.timestamp) return false;
        if (done != task.done) return false;
        return tasks != null ? tasks.equals(task.tasks) : task.tasks == null;
    }

    @Override
    public int hashCode(){
        int result = uid;
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (done ? 1 : 0);
        return result;
    }
}

