package com.example.cc.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NameDao {
    @Query("SELECT * FROM name WHERE name = :name LIMIT 1")
    Name findMovieByTitle(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Name... names);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Name name);

    @Query("DELETE FROM name")
    void deleteAll();

    @Query("SELECT * FROM name ORDER BY name ASC")
    LiveData<List<Name>> getAllBooks();
}
