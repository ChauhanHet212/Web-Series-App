package com.example.retrofitdemo.Models;

import java.io.Serializable;

public class Character implements Serializable {
    String name;
    Image image;

    public Character(String name, Image image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
