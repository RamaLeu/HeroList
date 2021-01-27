package com.example.heroapi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String ENTRY = "com.example.heroapi.ENTRY";
    private Context context;
    private LayoutInflater inflater;
    List<Hero> data;

    // create constructor to initialize context and data sent from SearchActivity
    public Adapter(Context context, List<Hero> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_hero, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        Hero current = data.get(position);
        myHolder.textName.setText(current.getName());
        myHolder.textStrength.setText("Strength: " + current.getStrength());
        myHolder.textSpeed.setText("Speed: " + current.getSpeed());
        myHolder.textFullName.setText("Full Name:  " + current.getFullName());
        myHolder.textWork.setText("Work: " +current.getWork());
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textName;
        TextView textStrength;
        TextView textSpeed;
        TextView textFullName;
        TextView textWork;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.textName);
            textStrength = (TextView) itemView.findViewById(R.id.textStrength);
            textSpeed = (TextView) itemView.findViewById(R.id.textSpeed);
            textFullName = (TextView) itemView.findViewById(R.id.textFullName);
            textWork = (TextView) itemView.findViewById(R.id.textWork);
            itemView.setOnClickListener(this);
        }

        // Click event for all items
        @Override
        public void onClick(View v) {
            Intent goToItem = new Intent(context, NewEntryActivity.class);
            int itemPosition = getAdapterPosition();
            Hero hero = data.get(itemPosition);
            goToItem.putExtra(ENTRY, hero);
            context.startActivity(goToItem);
        }
    }
}
