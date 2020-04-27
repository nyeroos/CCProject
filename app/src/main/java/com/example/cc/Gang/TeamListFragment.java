package com.example.cc.Gang;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cc.R;
import com.example.cc.database.Team;

import java.util.List;

public class TeamListFragment extends Fragment {
    private TeamListAdapter teamListAdapter;
    private TeamListViewModel teamViewModel;
    private Context context;

    public static TeamListFragment newInstance() {
        return new TeamListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        teamListAdapter = new TeamListAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_gang);
        recyclerView.setAdapter(teamListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    private void initData() {
        teamViewModel = new ViewModelProvider(this).get(TeamListViewModel.class);
        teamViewModel.getDirectorList().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable List<Team> teams) {
                teamListAdapter.setTeamList(teams);
            }
        });
    }

    public void removeData() {
        if (teamViewModel != null) {
            teamViewModel.deleteAll();
        }
    }
}
