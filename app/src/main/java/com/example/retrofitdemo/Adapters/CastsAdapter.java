package com.example.retrofitdemo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.Models.Casts;
import com.example.retrofitdemo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastsAdapter extends RecyclerView.Adapter<CastsAdapter.ViewHolder> {

    Context context;
    List<Casts> castsList;

    public CastsAdapter(Context context, List<Casts> castsList) {
        this.context = context;
        this.castsList = castsList;
    }

    @NonNull
    @Override
    public CastsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.casts_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CastsAdapter.ViewHolder holder, int position) {
        try {
            holder.card1.setVisibility(View.VISIBLE);
            Picasso.get().load(castsList.get(position).getPerson().getImage().getOriginal()).placeholder(R.drawable.placeholder).into(holder.cast_img);
        } catch (Exception e) {
            holder.card1.setVisibility(View.GONE);
        }
        holder.cast_name.setText(castsList.get(position).getPerson().getName());

        try {
            holder.card2.setVisibility(View.VISIBLE);
            Picasso.get().load(castsList.get(position).getCharacter().getImage().getOriginal()).placeholder(R.drawable.placeholder).into(holder.character_img);
        } catch (Exception e) {
            holder.card2.setVisibility(View.GONE);
        }
        holder.character_name.setText(castsList.get(position).getCharacter().getName());
    }

    @Override
    public int getItemCount() {
        return castsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cast_img, character_img;
        TextView cast_name, character_name;
        CardView card1, card2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cast_img = itemView.findViewById(R.id.cast_img);
            cast_name = itemView.findViewById(R.id.cast_name);
            character_img = itemView.findViewById(R.id.character_img);
            character_name = itemView.findViewById(R.id.character_name);
            card1 = itemView.findViewById(R.id.card1);
            card2 = itemView.findViewById(R.id.card2);
        }
    }
}
