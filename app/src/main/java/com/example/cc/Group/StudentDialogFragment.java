package com.example.cc.Group;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.cc.R;
import com.example.cc.database.NameDataBase;
import com.example.cc.database.Student;
import com.example.cc.database.StudentDao;
import com.example.cc.database.Team;
import com.example.cc.database.TeamDao;

public class StudentDialogFragment extends DialogFragment {
    private Context context;
    private String groupTitleExtra;
    private String groupGangFullNameExtra;

    private static final String EXTRA_GROUP_TITLE = "group_title";
    private static final String EXTRA_GROUP_GANG_FULL_NAME = "group_gang_full_name";
    public static final String TAG_DIALOG_BOOK_SAVE = "dialog_movie_save";

    public static StudentDialogFragment newInstance(final String movieTitle, final String groupGangFullName) {
        StudentDialogFragment fragment = new StudentDialogFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_GROUP_TITLE, movieTitle);
        args.putString(EXTRA_GROUP_GANG_FULL_NAME, groupGangFullName);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        groupTitleExtra = args.getString(EXTRA_GROUP_TITLE);
        groupGangFullNameExtra = args.getString(EXTRA_GROUP_GANG_FULL_NAME);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_group, null);
        final EditText groupEditText = view.findViewById(R.id.etGroupTitle);
        final EditText groupGangEditText = view.findViewById(R.id.etGroupGangFullName);
        if (groupTitleExtra != null) {
            groupEditText.setText(groupTitleExtra);
            groupEditText.setSelection(groupTitleExtra.length());
        }
        if (groupGangFullNameExtra != null) {
            groupGangEditText.setText(groupGangFullNameExtra);
            groupGangEditText.setSelection(groupGangFullNameExtra.length());
        }

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.dialog_group_title))
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveGroup(groupEditText.getText().toString(), groupGangEditText.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return alertDialogBuilder.create();
    }

    private void saveGroup(String groupTitle, String groupGangFullName) {
        if (TextUtils.isEmpty(groupTitle) || TextUtils.isEmpty(groupGangFullName)) {
            return;
        }

        TeamDao teamDao = NameDataBase.getNameDatabase(context).gangDao();
        StudentDao studentDao = NameDataBase.getNameDatabase(context).groupDao();

        int teamId = -1;
        if (groupGangFullNameExtra != null) {

            Team teamToUpdate = teamDao.findGangByName(groupGangFullNameExtra);
            if (teamToUpdate != null) {
                teamId = teamToUpdate.id;

                if (!teamToUpdate.num.equals(groupGangFullName)) {
                    teamToUpdate.num = groupGangFullName;
                    teamDao.update(teamToUpdate);
                }
            }
        } else {

            Team newTeam = teamDao.findGangByName(groupGangFullName);
            if (newTeam == null) {
                teamId = (int) teamDao.insert(new Team(groupGangFullName));
            } else {
                teamId = newTeam.id;
            }
        }

        if (groupTitleExtra != null) {

            Student studentToUpdate = studentDao.findGroupByTitle(groupTitleExtra);
            if (studentToUpdate != null) {
                if (!studentToUpdate.full_name.equals(groupTitle)) {
                    studentToUpdate.full_name = groupTitle;
                    if (teamId != -1) {
                        studentToUpdate.teamId = teamId;
                    }
                    studentDao.update(studentToUpdate);
                }
            }
        } else {

            Student newStudent = studentDao.findGroupByTitle(groupTitle);
            if (newStudent == null) {
                studentDao.insert(new Student(groupTitle, teamId));
            } else {
                if (newStudent.teamId != teamId) {
                    newStudent.teamId = teamId;
                    studentDao.update(newStudent);
                }
            }
        }
    }
}
