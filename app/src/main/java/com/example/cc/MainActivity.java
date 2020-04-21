package com.example.cc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cc.database.NameDao;
import com.example.cc.database.TeamDao;

public class MainActivity extends AppCompatActivity {

    private NameDao nameDao;
    private TeamDao teamDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameDao = ((AppDelegate) getApplicationContext()).getNameDataBase().getNameDao();
        teamDao = ((AppDelegate) getApplicationContext()).getNameDataBase().getTeamDao();

    }
}
