package com.example.cc.Gang;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cc.database.NameDataBase;
import com.example.cc.database.Team;
import com.example.cc.database.TeamDao;

import java.util.List;

public class TeamListViewModel extends AndroidViewModel {
    private TeamDao teamDao;
    private LiveData<List<Team>> gangLiveData;

    public TeamListViewModel(@NonNull Application application) {
        super(application);
        teamDao = NameDataBase.getNameDataBase(application).gangDao();
        gangLiveData = teamDao.getAllTeams();
    }

    public LiveData<List<Team>> getGangList() {
        return gangLiveData;
    }

    public void insert(Team... teams) {
        teamDao.insert(teams);
    }

    public void update(Team team) {
        teamDao.update(team);
    }

    public void deleteAll() {
        teamDao.deleteAll();
    }
}
