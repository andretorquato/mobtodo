package br.ufc.quixada.usoroomdatabase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import br.ufc.quixada.usoroomdatabase.database.AppDatabase;
import br.ufc.quixada.usoroomdatabase.models.Schedule;

public class DetailActivity extends AppCompatActivity {
    private TextView textViewNome;
    private TextView textViewData;
    private Button buttonDelete;
    private Button buttonBack;
    private AppDatabase db;
    private Schedule schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewNome = findViewById(R.id.textViewNome);
        textViewData = findViewById(R.id.textViewData);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonBack = findViewById(R.id.buttonBack);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "schedules-database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        int scheduleId = getIntent().getIntExtra("scheduleId", -1);
        schedule = db.scheduleDao().getScheduleById(scheduleId);

        if (schedule != null) {
            textViewNome.setText(schedule.name);
            textViewData.setText(schedule.date);
        }

        buttonDelete.setOnClickListener(v -> {
            db.scheduleDao().delete(schedule);
            finish();
        });

        buttonBack.setOnClickListener(v -> {
            finish();
        });
    }
}
