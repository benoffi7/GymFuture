package com.example.dami_.gymfuture.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "exercisesToDo" , foreignKeys = {
        @ForeignKey(entity = Day.class,parentColumns = "id_day",childColumns = "id_day", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Exercise.class,parentColumns = "id_exercise",childColumns = "id_exercise",
                onDelete = ForeignKey.CASCADE)
})
public class ExerciseToDo {
    @PrimaryKey @NonNull
    @ColumnInfo(name = "id_exerciseToDo")
    private String key;
    @ColumnInfo(name = "id_day")
    private String id_day;
    @ColumnInfo(name = "id_exercise")
    private String id_exercise;
    @ColumnInfo(name = "hasKilograms")
    private boolean hasKilograms;
    @ColumnInfo(name = "time")
    private String time;

    public ExerciseToDo(@NonNull String key, String id_day, String id_exercise, boolean hasKilograms, String time) {
        this.key = key;
        this.id_day = id_day;
        this.id_exercise = id_exercise;
        this.hasKilograms = hasKilograms;
        this.time = time;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    public String getId_day() {
        return id_day;
    }

    public void setId_day(String id_day) {
        this.id_day = id_day;
    }

    public String getId_exercise() {
        return id_exercise;
    }

    public void setId_exercise(String id_exercise) {
        this.id_exercise = id_exercise;
    }

    public boolean isHasKilograms() {
        return hasKilograms;
    }

    public void setHasKilograms(boolean hasKilograms) {
        this.hasKilograms = hasKilograms;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
