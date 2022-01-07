package com.aloevera.medicineapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.aloevera.medicineapp.data.DatabaseClient;
import com.aloevera.medicineapp.data.Medicine;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {

    private Context mCtx;
    private List<Medicine> medicineList;

    public MedicineAdapter(Context mCtx, List<Medicine> medicineList) {
        this.mCtx = mCtx;
        this.medicineList = medicineList;
    }

    @Override
    public MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks, parent, false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedicineViewHolder holder, int position) {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = DayOfWeek.from(today);

        Medicine t = medicineList.get(position);
        holder.medicineName.setText(t.getMedicineName());
        holder.frequency.setText(t.getFrequency());
        holder.days.setText(t.getDays());

    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView medicineName, frequency, days;
        Button drinkButton;
        public MedicineViewHolder(View itemView) {
            super(itemView);

            medicineName = itemView.findViewById(R.id.medicineName);
            days = itemView.findViewById(R.id.days);
            frequency = itemView.findViewById(R.id.frequency);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Medicine medicine = medicineList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, MainActivity.class);
            intent.putExtra("task", medicine);

            mCtx.startActivity(intent);
        }
    }
}
