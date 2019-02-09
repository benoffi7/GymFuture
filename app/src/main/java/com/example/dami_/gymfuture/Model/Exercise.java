package com.example.dami_.gymfuture.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "exercises")
public class Exercise {
    @PrimaryKey @NonNull
    @ColumnInfo(name = "id_exercise")
    private String idExercise;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;




    public Exercise(@NonNull String idExercise, String name, String description) {
        this.idExercise = idExercise;
        this.name = name;
        this.description = description;
    }

    @NonNull
    public String getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(@NonNull String idExercise) {
        this.idExercise = idExercise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
