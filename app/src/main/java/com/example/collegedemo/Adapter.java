package com.example.collegedemo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private static final String TAG = "Adapter";
    HomeActivity homeActivity;
    List<model> models;
    ArrayList<Integer> prices = new ArrayList<>();
    ArrayList<Integer> times = new ArrayList<>();
    OnClickCheck clickCheck;

    public Adapter(HomeActivity homeActivity, List<model> models, OnClickCheck clickCheck) {
        this.homeActivity = homeActivity;
        this.models = models;
        this.clickCheck = clickCheck;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pos_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.name.setText("Course: "+ models.get(i).name);
        holder.price.setText("Cost: $" + models.get(i).price);
        holder.time.setText("Time: " + models.get(i).time+" Hours");
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    prices.add(models.get(i).price);
                    times.add(models.get(i).time);
                } else {
                    prices.remove(new Integer(models.get(i).price));
                    times.remove(new Integer(models.get(i).time));
                }
                Log.e(TAG, "onCheckedChanged: " + prices.toString());
                if(prices.size()==0){

                }else{
                    int totP=0,totT = 0;
                    for(int i=0;i<prices.size();i++){
                        totP += prices.get(i);
                    }   for(int i=0;i<times.size();i++){
                        totT += times.get(i);
                    }

                  clickCheck.onClick(buttonView,totP,totT);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, time;
        CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            time = itemView.findViewById(R.id.time);
            cb = itemView.findViewById(R.id.cb);
        }
    }
}
