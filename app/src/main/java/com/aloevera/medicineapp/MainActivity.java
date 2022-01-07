package com.aloevera.medicineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.aloevera.medicineapp.data.DatabaseClient;
import com.aloevera.medicineapp.data.Medicine;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addNewMedicineButton,listAllMedicinesButton;
    TextView todayTitle;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        listAllMedicinesButton= findViewById(R.id.listAllMedicinesButton);
        listAllMedicinesButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),AllMedicines.class);
            startActivity(intent);
        });

        todayTitle = findViewById(R.id.todayTitle);


        LocalDate today = LocalDate.now();

        DayOfWeek dayOfWeek = DayOfWeek.from(today);

        Integer dayNumber =  dayOfWeek.getValue();
        String TodayTurkish = "";
        switch(dayNumber) {
            case 1:
                TodayTurkish = "Pazartesi";
                break;
            case 2:
                TodayTurkish = "Salı";
                break;
            case 3:
                TodayTurkish = "Çarşamba";
                break;
            case 4:
                TodayTurkish = "Perşembe";
                break;
            case 5:
                TodayTurkish = "Cuma";
                break;
            case 6:
                TodayTurkish = "Cumartesi";
                break;
            case 7:
                TodayTurkish = "Pazar";
                break;
            default:
                // code block
        }
        todayTitle.setText("Bugün Günlerden "+ TodayTurkish);

        recyclerView = findViewById(R.id.medicines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addNewMedicineButton = (Button) findViewById(R.id.addNewMedicineButton);
        addNewMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddMedicine.class);
                startActivity(intent);
            }
        });

        getMedicines(TodayTurkish);


    }

    private void getMedicines(String todayTurkish) {
        class GetMedicines extends AsyncTask<Void, Void, List<Medicine>> {

            @Override
            protected List<Medicine> doInBackground(Void... voids) {
                List<Medicine> medicineList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .medicineDao()
                        .findByDay(todayTurkish);
                return medicineList;
            }

            @Override
            protected void onPostExecute(List<Medicine> medicines) {
                super.onPostExecute(medicines);
                MedicineAdapter adapter = new MedicineAdapter(MainActivity.this, medicines);
                recyclerView.setAdapter(adapter);
            }
        }

        GetMedicines gt = new GetMedicines();
        gt.execute();
    }
}