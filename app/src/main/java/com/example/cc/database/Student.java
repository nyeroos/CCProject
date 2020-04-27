package com.example.cc.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "student",
        foreignKeys = @ForeignKey(entity = Team.class,
                parentColumns = "id",
                childColumns = "teamId",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index("full_name"), @Index("teamId")})
public class Student {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mid")
    public int id;

    @ColumnInfo(name = "full_name")
    @NonNull
    public String full_name;

    @ColumnInfo(name = "teamId")
    public int teamId;

    public Student(@NonNull String full_name, int teamId) {
        this.full_name = full_name;
        this.teamId = teamId;
    }
}
