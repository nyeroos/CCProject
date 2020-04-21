package com.example.cc;

import android.app.Application;

import androidx.room.Room;

import com.example.cc.database.NameDataBase;

public class AppDelegate extends Application {

    public NameDataBase nameDataBase;

    @Override
    public void onCreate() {
        super.onCreate();

        nameDataBase = Room.databaseBuilder(getApplicationContext(),NameDataBase.class,"name_db").build();
    }

    public NameDataBase getNameDataBase(){
        return nameDataBase;
    }
}
