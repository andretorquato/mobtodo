package br.ufc.quixada.usoroomdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import java.util.List;
import br.ufc.quixada.usoroomdatabase.database.AppDatabase;
import br.ufc.quixada.usoroomdatabase.models.Schedule;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private ScheduleAdapter scheduleAdapter;
    private RecyclerView recyclerView;
    private Button redirectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        redirectButton = findViewById(R.id.redirectButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "schedules-database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();


        List<Schedule> schedules = db.scheduleDao().getAllSchedules();
        scheduleAdapter = new ScheduleAdapter(schedules, this);
        recyclerView.setAdapter(scheduleAdapter);;


        redirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Schedule> allSchedules = db.scheduleDao().getAllSchedules();
        scheduleAdapter = new ScheduleAdapter(allSchedules, this);
        recyclerView.setAdapter(scheduleAdapter);
    }
}
