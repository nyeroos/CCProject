package com.example.cc.Group;

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
import com.example.cc.database.Student;

import java.util.List;

public class StudentListFragment extends Fragment {

    private StudentListAdapter groupListAdapter;
    private StudentViewModel groupViewModel;
    private Context context;

    public static StudentListFragment newInstance() {
        return new StudentListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        groupListAdapter = new StudentListAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_group);
        recyclerView.setAdapter(groupListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    private void initData() {
        groupViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        groupViewModel.getGroupList().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> books) {
                groupListAdapter.setStudentList(books);
            }
        });
    }

    public void removeData() {
        if (groupListAdapter != null) {
            groupViewModel.deleteAll();
        }
    }
}
