package br.ufc.quixada.usoroomdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import br.ufc.quixada.usoroomdatabase.database.AppDatabase;
import br.ufc.quixada.usoroomdatabase.models.Task;

public class TaskActivity extends AppCompatActivity {
    private AppDatabase db;
    private TaskAdapter taskAdapter;
    private RecyclerView listRecyclerView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);

        listRecyclerView = findViewById(R.id.listRecyclerView);
        backButton = findViewById(R.id.backButton);

        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "task-database").allowMainThreadQueries().build();

        List<Task> tasks = db.taskDao().getAllTasks();
        taskAdapter = new TaskAdapter(tasks);
        listRecyclerView.setAdapter(taskAdapter);

        for (int i = 0; i < tasks.size(); i++) {
            Log.d("Task", "Array element at index " + i + ": " + tasks.get(i));
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
