package com.example.traininghepler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder> {

    private List<Training> trainingList;

    public TrainingAdapter(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_training, parent, false);
        return new TrainingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {
        Training training = trainingList.get(position);
        holder.textName.setText(training.getName());
        
        holder.textReps.setText("Ilość serii: " + training.getReps() + "x");
        
        holder.textDate.setText(training.getDate());
    }

    @Override
    public int getItemCount() {
        return trainingList.size();
    }

    public static class TrainingViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textReps, textDate;

        public TrainingViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textReps = itemView.findViewById(R.id.textReps);
            textDate = itemView.findViewById(R.id.textDate);
        }
    }
}
