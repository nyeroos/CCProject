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

import com.example.cc.AppDelegate;
import com.example.cc.R;
import com.example.cc.database.NameDataBase;
import com.example.cc.database.Team;
import com.example.cc.database.TeamDao;


public class TeamDialogFragment extends DialogFragment {
    private Context context;
    private String teamNumber;

    private static final String EXTRA_TEAM_NUMBER = "team_number";
    public static final String TAG_DIALOG_TEAM_SAVE = "team_physic_save";

    public static TeamDialogFragment newInstance(String directorFullName) {
        TeamDialogFragment fragment = new TeamDialogFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_TEAM_NUMBER, directorFullName);
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
        teamNumber = args.getString(EXTRA_TEAM_NUMBER);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_physic, null);
        final EditText directorEditText = view.findViewById(R.id.etPhysicNumber);
        if (teamNumber != null) {
            directorEditText.setText(teamNumber);
            directorEditText.setSelection(teamNumber.length());
        }

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.dialog_physic_title))
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        savePhysic(directorEditText.getText().toString());
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

    private void savePhysic(String number) {
        if (TextUtils.isEmpty(number)) {
            return;
        }

        TeamDao teamDao = NameDataBase.getNameDataBase().groupDao();

        if (teamNumber != null) {
            Team teamToUpdate = teamDao.findPhysicByName(teamNumber);
            if (teamToUpdate != null) {
                if (!teamToUpdate.num.equals(number)) {
                    teamToUpdate.num = number;
                    teamDao.update(teamToUpdate);
                }
            }
        } else {
            teamDao.insert(new Team(number));
        }
    }
}
