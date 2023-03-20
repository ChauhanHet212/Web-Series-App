package com.example.retrofitdemo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.InfoActivity;
import com.example.retrofitdemo.Models.Shows;
import com.example.retrofitdemo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    Context context;
    List<Shows> allMovies;

    public MoviesAdapter(Context context, List<Shows> allMovies) {
        this.context = context;
        this.allMovies = allMovies;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.movies_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
        try {
            Picasso.get().load(allMovies.get(position).getImage().getOriginal()).placeholder(R.drawable.placeholder).into(holder.movieImg);
        } catch (Exception e) {
            holder.movieImg.setImageResource(R.drawable.placeholder);
        }
        holder.movieName.setText(allMovies.get(position).getName());
        String type = "";
        for (int i = 0; i < allMovies.get(position).getGenres().length; i++) {
            if (i == allMovies.get(position).getGenres().length - 1){
                type += allMovies.get(position).getGenres()[i];
            } else {
                type += allMovies.get(position).getGenres()[i] + ", ";
            }
        }
        holder.movieType.setText(type);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("Movie", allMovies.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    public void filter(List<Shows> filteredList){
        allMovies = filteredList;
        notifyDataSetChanged();
    }

    public void reset(List<Shows> oldShows){
        allMovies = oldShows;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImg;
        TextView movieName, movieType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImg = itemView.findViewById(R.id.movieImg);
            movieName = itemView.findViewById(R.id.movieName);
            movieType = itemView.findViewById(R.id.movieType);
        }
    }
}
