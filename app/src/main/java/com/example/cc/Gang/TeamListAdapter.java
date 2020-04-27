package com.example.cc.Gang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cc.R;
import com.example.cc.database.Team;

import java.util.List;

import static com.example.cc.Gang.TeamDialogFragment.TAG_DIALOG_TEAM_SAVE;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.GangViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Team> teamList;
    private Context context;

    public TeamListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setTeamList(List<Team> authorList) {
        this.teamList = authorList;
        notifyDataSetChanged();
    }

    @Override
    public TeamListAdapter.GangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_gang, parent, false);
        return new TeamListAdapter.GangViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TeamListAdapter.GangViewHolder holder, int position) {
        if (teamList == null) {
            return;
        }

        final Team team = teamList.get(position);
        if (team != null) {
            holder.gangText.setText(team.num);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialogFragment = TeamDialogFragment.newInstance(team.num);
                    dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), TAG_DIALOG_TEAM_SAVE);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (teamList == null) {
            return 0;
        } else {
            return teamList.size();
        }
    }

    static class GangViewHolder extends RecyclerView.ViewHolder {
        private TextView gangText;

        public GangViewHolder(View itemView) {
            super(itemView);
            gangText = itemView.findViewById(R.id.tvGang);
        }
    }
}
