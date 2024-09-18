package br.ufc.quixada.usoroomdatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufc.quixada.usoroomdatabase.models.Schedule;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private List<Schedule> schedules;
    private Context context;

    public ScheduleAdapter(List<Schedule> schedules, Context context) {

        this.schedules = schedules;
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleAdapter.ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_item, parent, false);
        return new ScheduleAdapter.ScheduleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ScheduleViewHolder holder, int position) {
        Schedule schedule = schedules.get(position);
        holder.nameTextView.setText(schedule.name);
        holder.dateTextView.setText(schedule.date);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("scheduleId", schedule.uid);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public void removeSchedule(int position) {
        schedules.remove(position);
        notifyItemRemoved(position);
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
        notifyItemInserted(schedules.size() - 1);
    }

    public Schedule getScheduleAt(int position) {
        return schedules.get(position);
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, dateTextView;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
