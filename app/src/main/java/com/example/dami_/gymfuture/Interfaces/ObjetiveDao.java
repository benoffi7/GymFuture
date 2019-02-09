package com.example.dami_.gymfuture.Interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dami_.gymfuture.Model.Objetive;

import java.util.List;

@Dao
public interface ObjetiveDao {
    @Insert
    void add(Objetive... objetives);

    @Update
    void update(Objetive... objetives);

    @Delete
    void delete(Objetive... objetives);

    @Query("DELETE FROM objetives")
    void truncateTable();

    @Query("SELECT * FROM objetives")
    List<Objetive> getAll();

    @Query("SELECT * FROM objetives WHERE name = :name LIMIT 1")
    Objetive getByName(String name);

    @Query("SELECT * FROM objetives WHERE id_objetive = :id")
    Objetive getById(String id);
}
