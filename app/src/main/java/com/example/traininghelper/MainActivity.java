package com.example.traininghelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TrainingDbHelper dbHelper;
    private TrainingAdapter adapter;
    private List<Training> trainingList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new TrainingDbHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button testButton = findViewById(R.id.bttn);
        testButton.setOnClickListener(v -> {
            startActivity(new Intent(this, AddTraining.class));
        });

        loadDataFromDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        trainingList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                TrainingDbHelper.TABLE_NAME,
                null, null, null, null, null,
                TrainingDbHelper.COLUMN_ID + " DESC"
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(TrainingDbHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(TrainingDbHelper.COLUMN_NAME));
                int reps = cursor.getInt(cursor.getColumnIndexOrThrow(TrainingDbHelper.COLUMN_REPS));
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(TrainingDbHelper.COLUMN_DURATION));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(TrainingDbHelper.COLUMN_DATE));
                String difficulty = cursor.getString(cursor.getColumnIndexOrThrow(TrainingDbHelper.COLUMN_DIFFICULTY));

                trainingList.add(new Training(id, name, reps, duration, date, difficulty));
            }
            cursor.close();
        }


        if (trainingList.isEmpty()) {
            Toast.makeText(this, "Brak zapisanych treningów. Dodaj pierwszy!", Toast.LENGTH_LONG).show();
        }

        if (adapter == null) {
            adapter = new TrainingAdapter(trainingList);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
