package com.example.cc.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Name.class, Team.class}, version = 2)
public abstract class NameDataBase extends RoomDatabase {
    public static NameDataBase instance;

//    public static NameDataBase getDatabase(final Context context){
//
//        instance = Room.databaseBuilder(context.getApplicationContext(),NameDataBase.class, "book.db")
//                @Override
//            public void onCreate(@NonNull SupportSQLiteDatabase database){
//            super.onCreate(database);
//            new PopulateDbAsync(instance).execute();
//                }
//                .build();
//    }
//}
