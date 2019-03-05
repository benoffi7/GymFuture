package com.example.dami_.gymfuture.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user_objetive",foreignKeys =
    @ForeignKey(entity = Objetive.class,parentColumns = "id_objetive", childColumns = "id_objetive" ,
            onDelete = ForeignKey.SET_NULL)
)
public class UserObjetive {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "id_objetive")
    private String id_objetive;

    public UserObjetive() {
        this.id_objetive = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_objetive() {
        return id_objetive;
    }

    public void setId_objetive(String id_objetive) {
        this.id_objetive = id_objetive;
    }

}
