package com.aloevera.medicineapp.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity
public class Medicine implements Serializable {



    @PrimaryKey(autoGenerate = true)
    public int medicine_id;

    @ColumnInfo(name = "medicine_name")
    public String medicineName;

    @ColumnInfo(name = "days")
    public String days;

    @ColumnInfo(name = "frequency")
    public String frequency;

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
