package com.example.cc.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

public class Team {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "did")
    public int id;

    @ColumnInfo(name = "num")
    @NonNull
    public String num;

    public Team(@NonNull String fullName) {
        this.num = num;
    }

}
