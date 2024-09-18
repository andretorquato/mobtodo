package br.ufc.quixada.usoroomdatabase.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "schedule")
public class Schedule {
    // : id, name, date
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "date")
    public String date;



    public Schedule(String name, String date){
        this.name = name;
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return this.uid + " | " + this.name + " | " + this.date;
    }
}
