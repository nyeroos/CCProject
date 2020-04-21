package com.example.cc.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Name.class, Team.class}, version = 2)
public abstract class NameDataBase extends RoomDatabase {

    public abstract NameDao getNameDao();
    public abstract TeamDao getTeamDao();
}
