package com.example.mybajnoksag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private ArrayList<Team> teamsData;
    private Context context;

    public TeamAdapter(Context context, ArrayList<Team> itemsData) {
        this.teamsData = itemsData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.table, parent, false));
    }

    @Override
    public void onBindViewHolder(TeamAdapter.ViewHolder holder, int position) {
        Team currentItem = teamsData.get(position);

        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return teamsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView win;
        private TextView draw;
        private TextView lose;
        private TextView points;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            win = itemView.findViewById(R.id.win);
            draw = itemView.findViewById(R.id.draw);
            lose = itemView.findViewById(R.id.lose);
            points = itemView.findViewById(R.id.points);

        }

        public void bindTo(Team currentItem) {
            name.setText(currentItem.getName());
            win.setText(currentItem.getWin());
            draw.setText(currentItem.getDraw());
            lose.setText(currentItem.getLose());
            points.setText(currentItem.getPoints());
        }
    }

}


