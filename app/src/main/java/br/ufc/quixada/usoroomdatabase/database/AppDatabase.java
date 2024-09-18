package br.ufc.quixada.usoroomdatabase.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.ufc.quixada.usoroomdatabase.dao.ScheduleDao;
import br.ufc.quixada.usoroomdatabase.models.Schedule;

@Database(entities = {Schedule.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScheduleDao scheduleDao();
}

