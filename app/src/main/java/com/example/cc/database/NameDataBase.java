package com.example.cc.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class, Team.class}, version = 2)
public abstract class NameDataBase extends RoomDatabase {

    public abstract StudentDao getNameDao();
    public abstract TeamDao getTeamDao();
}
