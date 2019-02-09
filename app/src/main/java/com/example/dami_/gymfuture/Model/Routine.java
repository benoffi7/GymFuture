package com.example.dami_.gymfuture.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "routines" , foreignKeys = {
        @ForeignKey(entity = Objetive.class, parentColumns = "id_objetive" , childColumns = "id_objetive",
                onUpdate = ForeignKey.SET_DEFAULT , onDelete = ForeignKey.SET_NULL
        )
})
public class Routine {
    @PrimaryKey @NonNull
    @ColumnInfo(name = "id_routine")
    private String key;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "id_objetive")
    private String id_objetive;

    public Routine(@NonNull String key, String name, String id_objetive) {
        this.key = key;
        this.name = name;
        this.id_objetive = id_objetive;
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

    public String getId_objetive() {
        return id_objetive;
    }

    public void setId_objetive(String id_objetive) {
        this.id_objetive = id_objetive;
    }
}
