package br.ufc.quixada.usoroomdatabase;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.ufc.quixada.usoroomdatabase.database.AppDatabase;
import br.ufc.quixada.usoroomdatabase.models.Schedule;

public class AddItemActivity extends AppCompatActivity {

    private EditText nameEditText, dateEditText;
    private Button saveButton, showItemsButton;
    private Calendar calendar = Calendar.getInstance();
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameEditText = findViewById(R.id.nameEditText);
        dateEditText = findViewById(R.id.dateEditText);
        saveButton = findViewById(R.id.saveButton);
        showItemsButton = findViewById(R.id.showListButton);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "schedules-database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();


        dateEditText.setOnClickListener(v -> showDateTimePicker());

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String date = dateEditText.getText().toString();

            if (name.isEmpty() || date.isEmpty()) {
                Toast.makeText(AddItemActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            Schedule newSchedule = new Schedule(name, date);
            db.scheduleDao().insertAll(newSchedule);
            List<Schedule> allSchedules = db.scheduleDao().getAllSchedules();

            Toast.makeText(AddItemActivity.this, "Agendamento salvo!", Toast.LENGTH_SHORT).show();
            nameEditText.setText("");
            dateEditText.setText("");
        });

        showItemsButton.setOnClickListener(v -> finish());
    }

    @SuppressLint("DefaultLocale")
    private void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        new DatePickerDialog(AddItemActivity.this, (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(year, monthOfYear, dayOfMonth);

            new TimePickerDialog(AddItemActivity.this, (timeView, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, 0);

                dateEditText.setText(String.format("%02d/%02d/%04d %02d:00",
                        dayOfMonth, (monthOfYear + 1), year, hourOfDay));
            }, currentDate.get(Calendar.HOUR_OF_DAY), 0, true).show();

        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH)).show();
    }
}
