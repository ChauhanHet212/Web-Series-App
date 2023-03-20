package com.example.retrofitdemo;

import com.example.retrofitdemo.Models.Casts;
import com.example.retrofitdemo.Models.SearchedShow;
import com.example.retrofitdemo.Models.Shows;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class Retro {

    public String url = "https://api.tvmaze.com/";
    public static Retro instance;
    RetroAPI retroAPI;

    public Retro(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retroAPI = retrofit.create(RetroAPI.class);
    }

    public static Retro getInstance(){
        if (instance == null){
            instance = new Retro();
        }

        return instance;
    }

    public interface RetroAPI {
        @GET("shows")
        Call<List<Shows>> getAllShows();

        @GET("search/shows")
        Call<List<SearchedShow>> getSearchedShows(@Query("q") String name);

        @GET("shows/{id}/cast")
        Call<List<Casts>> getCasts(@Path("id") int id);
    }
}
