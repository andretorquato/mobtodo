package br.ufc.quixada.usoroomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.ufc.quixada.usoroomdatabase.models.Task;

@Dao
public interface TaskDao {
    @Insert
    void insertAll(Task... tasks);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM task")
    List<Task> getAllTasks();
}
