package com.example.dami_.gymfuture.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "days" , foreignKeys = {
        @ForeignKey(entity = Routine.class, parentColumns = "id_routine" , childColumns = "id_routine", onDelete = ForeignKey.CASCADE)
})
public class Day {
    @PrimaryKey @NonNull
    @ColumnInfo(name = "id_day")
    private String key;
    @ColumnInfo(name = "number")
    private Byte number;
    @ColumnInfo(name = "id_routine")
    private String id_routine;

    public Day(@NonNull String key, Byte number, String id_routine) {
        this.key = key;
        this.number = number;
        this.id_routine = id_routine;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    public Byte getNumber() {
        return number;
    }

    public void setNumber(Byte number) {
        this.number = number;
    }

    public String getId_routine() {
        return id_routine;
    }

    public void setId_routine(String id_routine) {
        this.id_routine = id_routine;
    }
}
