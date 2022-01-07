package com.aloevera.medicineapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Medicine.class},version = 1)
public abstract class MedicineAppDatabase extends RoomDatabase {
    public abstract MedicineDao medicineDao();
}
