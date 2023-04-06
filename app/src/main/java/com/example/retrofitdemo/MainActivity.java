package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.retrofitdemo.Adapters.MoviesAdapter;
import com.example.retrofitdemo.Models.SearchedShow;
import com.example.retrofitdemo.Models.Shows;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Shows> allShows = new ArrayList<>();
    List<Shows> filteredList = new ArrayList<>();
    RecyclerView moviesRecycler;
    MoviesAdapter moviesAdapter;
    ProgressBar progressBar;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesRecycler = findViewById(R.id.moviesRecycler);
        progressBar = findViewById(R.id.progressBar);
        searchView = findViewById(R.id.search_bar);

        progressBar.setVisibility(View.VISIBLE);

        Retro.getInstance().retroAPI.getAllShows().enqueue(new Callback<List<Shows>>() {
            @Override
            public void onResponse(Call<List<Shows>> call, Response<List<Shows>> response) {
                allShows.addAll(response.body());
                progressBar.setVisibility(View.GONE);
                moviesRecycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                moviesAdapter = new MoviesAdapter(MainActivity.this, allShows);
                moviesRecycler.setAdapter(moviesAdapter);
            }

            @Override
            public void onFailure(Call<List<Shows>> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);

                Retro.getInstance().retroAPI.getSearchedShows(query).enqueue(new Callback<List<SearchedShow>>() {
                    @Override
                    public void onResponse(Call<List<SearchedShow>> call, Response<List<SearchedShow>> response) {
                        filteredList.clear();
                        for (int i = 0; i < response.body().size(); i++) {
                            filteredList.add(response.body().get(i).getShow());
                        }
                        moviesAdapter.filter(filteredList);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<List<SearchedShow>> call, Throwable t) {

                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    moviesAdapter.reset(allShows);
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.exit_dialog);

        dialog.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        dialog.findViewById(R.id.noBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}