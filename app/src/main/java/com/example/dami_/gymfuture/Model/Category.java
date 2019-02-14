package com.example.dami_.gymfuture.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey @NonNull
    @ColumnInfo(name = "id_category")
    private String key;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "imageUrl")
    private String image_url;

    public Category(@NonNull String key, String name, String image_url) {
        this.key = key;
        this.name = name;
        this.image_url = image_url;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
