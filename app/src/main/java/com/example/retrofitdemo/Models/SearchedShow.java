package com.example.retrofitdemo.Models;

public class SearchedShow {
    float score;
    Shows show;

    public SearchedShow(float score, Shows show) {
        this.score = score;
        this.show = show;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Shows getShow() {
        return show;
    }

    public void setShow(Shows show) {
        this.show = show;
    }
}
