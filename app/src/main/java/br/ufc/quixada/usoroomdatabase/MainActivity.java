package br.ufc.quixada.usoroomdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private TaskAdapter taskAdapter;
    private EditText titleEditText, descriptionEditText;
    private Button saveButton;
    private Button listButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        saveButton = findViewById(R.id.saveButton);
        listButton = findViewById(R.id.listButton);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "task-database").allowMainThreadQueries().build();
        List<Task> tasks = db.taskDao().getAllTasks();
        taskAdapter = new TaskAdapter(tasks);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)) {
                    Task newTask = new Task(title, description);

                    db.taskDao().insertAll(newTask);

                    taskAdapter.addTask(newTask);

                    titleEditText.setText("");
                    descriptionEditText.setText("");
                    Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                    startActivity(intent);
                }
            }
        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });
    }
}
