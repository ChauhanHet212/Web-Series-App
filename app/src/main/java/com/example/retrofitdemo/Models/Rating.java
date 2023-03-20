package com.example.retrofitdemo.Models;

import java.io.Serializable;

public class Rating implements Serializable {
    float average;

    public Rating(float average) {
        this.average = average;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}
