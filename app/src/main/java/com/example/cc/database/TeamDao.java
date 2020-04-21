package com.example.cc.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TeamDao {
    @Query("SELECT * FROM Team WHERE did = :id LIMIT 1")
    Team findDirectorById(int id);

    @Query("SELECT * FROM Team WHERE num = :num LIMIT 1")
    Team findDirectorByName(String num);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Team team);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Team... teams);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Team team);

    @Query("DELETE FROM Team")
    void deleteAll();

    @Query("SELECT * FROM Team ORDER BY num ASC")
    LiveData<List<Team>> getAllTeams();
}
