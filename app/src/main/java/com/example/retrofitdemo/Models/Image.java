package com.example.retrofitdemo.Models;

import java.io.Serializable;

public class Image implements Serializable {
    String medium, original;

    public Image(String medium, String original) {
        this.medium = medium;
        this.original = original;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
