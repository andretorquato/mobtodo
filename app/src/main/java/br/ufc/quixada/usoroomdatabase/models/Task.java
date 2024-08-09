package br.ufc.quixada.usoroomdatabase.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")
public class Task {
    // : id, title, description
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "description")
    public String description;


    public Task(String title, String description){
        this.title = title;
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return this.uid + " | " + this.title + " | " + this.description;
    }
}
