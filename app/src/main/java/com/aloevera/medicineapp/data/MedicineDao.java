package com.aloevera.medicineapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicineDao {
    @Query("SELECT * FROM medicine")
    List<Medicine> getAll();

    @Query("SELECT * FROM medicine WHERE days LIKE '%' ||:day ||'%'")
    List<Medicine> findByDay(String day);

    @Query("SELECT * FROM medicine WHERE medicine_id IN (:medicineIds)")
    List<Medicine> loadAllByIds(int[] medicineIds);

    @Query("SELECT * FROM medicine WHERE medicine_name LIKE :medicineName " +
            " LIMIT 1")
    Medicine findByName(String medicineName);

    @Query("SELECT * FROM medicine WHERE medicine_id LIKE :medicineId " +
            " LIMIT 1")
    Medicine findByName(Integer medicineId);

    @Insert
    void insert(Medicine... medicines);

    @Delete
    void delete(Medicine medicine);
}
