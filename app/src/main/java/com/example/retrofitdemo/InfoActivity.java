package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.retrofitdemo.Adapters.CastsAdapter;
import com.example.retrofitdemo.Models.Casts;
import com.example.retrofitdemo.Models.Shows;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {

    ImageView main_bg, infoImg;
    Shows movie;
    TextView infoName, infoType, infoLanguage, infoStatus, infoPremiered, infoEnded, infoRuntime, infoRating, infoSite, infoDis;
    RecyclerView peoplesRecycler;
    ProgressBar progress;
    CastsAdapter adapter;

    List<Casts> castsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        main_bg = findViewById(R.id.main_bg);
        infoImg = findViewById(R.id.infoImg);
        infoName = findViewById(R.id.infoName);
        infoType = findViewById(R.id.infoType);
        infoLanguage = findViewById(R.id.infoLanguage);
        infoStatus = findViewById(R.id.infoStatus);
        infoPremiered = findViewById(R.id.infoPremiered);
        infoEnded = findViewById(R.id.infoEnded);
        infoRuntime = findViewById(R.id.infoRuntime);
        infoRating = findViewById(R.id.infoRating);
        infoSite = findViewById(R.id.infoSite);
        infoDis = findViewById(R.id.infoDis);
        peoplesRecycler = findViewById(R.id.peoplesRecycler);
        progress = findViewById(R.id.progress);

        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        movie = (Shows) getIntent().getSerializableExtra("Movie");


        Retro.getInstance().retroAPI.getCasts(movie.getId()).enqueue(new Callback<List<Casts>>() {
            @Override
            public void onResponse(Call<List<Casts>> call, Response<List<Casts>> response) {
                castsList.addAll(response.body());
                progress.setVisibility(View.GONE);
                if (castsList != null) {
                    findViewById(R.id.cast_textv).setVisibility(View.VISIBLE);
                    peoplesRecycler.setLayoutManager(new LinearLayoutManager(InfoActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    adapter = new CastsAdapter(InfoActivity.this, castsList);
                    peoplesRecycler.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Casts>> call, Throwable t) {

            }
        });

        try {
            Picasso.get().load(movie.getImage().getOriginal()).placeholder(R.drawable.placeholder).into(main_bg);
        } catch (Exception e) {
            main_bg.setImageResource(R.drawable.placeholder);
        }
        try {
            Picasso.get().load(movie.getImage().getOriginal()).placeholder(R.drawable.placeholder).into(infoImg);
        } catch (Exception e) {
            infoImg.setImageResource(R.drawable.placeholder);
        }
        infoName.setText(movie.getName());
        String type = "";
        for (int i = 0; i < movie.getGenres().length; i++) {
            if (i == movie.getGenres().length - 1){
                type += movie.getGenres()[i];
            } else {
                type += movie.getGenres()[i] + ",\n";
            }
        }
        infoType.setText(type);
        infoLanguage.setText(movie.getLanguage());
        infoStatus.setText(movie.getStatus());
        infoPremiered.setText(movie.getPremiered());
        if (movie.getEnded() != null){
            infoEnded.setText(movie.getEnded());
        } else {
            infoEnded.setText("-");
        }
        infoRuntime.setText(String.valueOf(movie.getRuntime()));
        infoRating.setText(movie.getRating().getAverage() + "/10");
        if (movie.getOfficialSite() != null){
            infoSite.setText(movie.getOfficialSite());
            infoSite.setTextColor(getResources().getColor(R.color.purple));
            infoSite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getOfficialSite())), "Open With"));
                }
            });
        } else {
            infoSite.setText("-");
        }
        if (movie.getSummary() != null) {
            infoDis.setText(HtmlCompat.fromHtml(movie.getSummary(), 0));
        } else {
            infoDis.setText("-");
        }
    }
}