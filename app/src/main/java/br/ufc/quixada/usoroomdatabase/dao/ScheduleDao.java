package br.ufc.quixada.usoroomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.ufc.quixada.usoroomdatabase.models.Schedule;

@Dao
public interface ScheduleDao {
    @Insert
    void insertAll(Schedule... schedules);

    @Delete
    void delete(Schedule schedule);

    @Query("SELECT * FROM schedule")
    List<Schedule> getAllSchedules();

    @Query("SELECT * FROM schedule WHERE uid = :id")
    Schedule getScheduleById(int id);

}
