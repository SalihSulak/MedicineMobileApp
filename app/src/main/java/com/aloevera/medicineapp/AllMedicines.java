package com.aloevera.medicineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.aloevera.medicineapp.data.DatabaseClient;
import com.aloevera.medicineapp.data.Medicine;

import java.lang.ref.WeakReference;
import java.util.List;

public class AllMedicines extends AppCompatActivity {
    private RecyclerView recyclerView;
    Button addNewMedicineButton,listAllMedicinesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_all_medicines);
 
        recyclerView = findViewById(R.id.medicines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listAllMedicinesButton= findViewById(R.id.listAllMedicinesButton);
        listAllMedicinesButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });

        addNewMedicineButton = (Button) findViewById(R.id.addNewMedicineButton);
        addNewMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddMedicine.class);
                startActivity(intent);
            }
        });

        getAllMedicines();

}
    private void getAllMedicines() {
        class getAllMedicines extends AsyncTask<Void, Void, List<Medicine>> {

            @Override
            protected List<Medicine> doInBackground(Void... voids) {
                List<Medicine> medicineList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .medicineDao()
                        .getAll();
                return medicineList;
            }

            @Override
            protected void onPostExecute(List<Medicine> medicines) {
                super.onPostExecute(medicines);
                MedicineAdapter adapter = new MedicineAdapter(AllMedicines.this, medicines);
                recyclerView.setAdapter(adapter);
            }
        }

        getAllMedicines gt = new getAllMedicines();
        gt.execute();
    }
}