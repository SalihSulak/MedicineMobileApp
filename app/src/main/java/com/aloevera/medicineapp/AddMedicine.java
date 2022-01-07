package com.aloevera.medicineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.aloevera.medicineapp.data.DatabaseClient;
import com.aloevera.medicineapp.data.Medicine;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AddMedicine extends AppCompatActivity {

    private EditText medicineNameInput,frequencyInput;
    private CheckBox pzt,sali,cars,pers,cuma,cmt,pazar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_medicine);

        medicineNameInput = findViewById(R.id.medicineNameInput);
        frequencyInput = findViewById(R.id.frequencyInput);

        pzt =  findViewById(R.id.pzt);
        sali = findViewById(R.id.sali);
        cars = findViewById(R.id.cars);
        pers = findViewById(R.id.pers);
        cuma = findViewById(R.id.cuma);
        cmt =  findViewById(R.id.cmt);
        pazar =findViewById(R.id.pazar);


        findViewById(R.id.saveMedicineButton).setOnClickListener(view ->  {
                saveMedicine();
        });
        findViewById(R.id.myMedicines).setOnClickListener(view ->  {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });
    }

        private void saveMedicine(){
            final String sMedicineName = medicineNameInput.getText().toString().trim();
            final String sFrequency = frequencyInput.getText().toString().trim();


            //final String daysListe = pzt.isChecked()+","+sali.isChecked()+","+cars.isChecked()+","+pers.isChecked()+","+cuma.isChecked()+","+cmt.isChecked()+","+pazar.isChecked();
                    ArrayList<String> daysList = new ArrayList<>() ;
            if(pzt.isChecked())
                daysList.add(pzt.getText().toString());
            if(sali.isChecked())
                daysList.add( sali.getText().toString());
            if(cars.isChecked())
                daysList.add(cars.getText().toString());
            if(pers.isChecked())
                daysList.add(pers.getText().toString());
            if(cuma.isChecked())
                daysList.add(cuma.getText().toString());
            if(cmt.isChecked())
                daysList.add(cmt.getText().toString());
            if(pazar.isChecked())
                daysList.add(pazar.getText().toString());

            String listString = daysList.stream().map(Object::toString)
                    .collect(Collectors.joining(", "));

            if (sMedicineName.isEmpty()) {
                medicineNameInput.setError("İlaç adı gereklidir.");
                medicineNameInput.requestFocus();
                return;
            }

            if (sFrequency.isEmpty()) {
                frequencyInput.setError("Kullanma sıklığı gereklidir.");
                frequencyInput.requestFocus();
                return;
            }

            class SaveTask extends AsyncTask<Void, Void, Void> {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();

                }

                @Override
                protected Void doInBackground(Void... voids) {

                    //creating a task
                    Medicine medicine = new Medicine();
                    medicine.setMedicineName(sMedicineName);
                    medicine.setFrequency(sFrequency);
                    medicine.setDays(listString);
                    //adding to database
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .medicineDao()
                            .insert(medicine);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                }

            }

            SaveTask st = new SaveTask();
            st.execute();

        }


}