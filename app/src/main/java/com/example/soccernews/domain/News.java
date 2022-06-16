package com.example.soccernews.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News {
    @PrimaryKey
    public int id;
    public String title;
    public String image;
    public String link;
    public String description;
    public boolean favorite;
}
