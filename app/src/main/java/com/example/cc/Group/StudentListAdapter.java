package com.example.cc.Group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cc.R;
import com.example.cc.database.NameDataBase;
import com.example.cc.database.Student;
import com.example.cc.database.Team;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Student> studentList;
    private Context context;

    public StudentListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        notifyDataSetChanged();
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_group, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BooksViewHolder holder, int position) {
        if (studentList == null) {
            return;
        }

        final Student student = studentList.get(position);
        if (student != null) {
            holder.titleText.setText(student.title);

            final Team team = NameDataBase.getNameDatabase(context).gangDao().findGangById(student.teamId);
            final String teamName;
            if (team != null) {
                holder.directorText.setText(team.num);
                teamName = team.num;
            } else {
                teamName = "";
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialogFragment = StudentDialogFragment.newInstance(student.full_name, teamName);
                    dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), TAG_DIALOG_STUDENT_SAVE);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (studentList == null) {
            return 0;
        } else {
            return studentList.size();
        }
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private TextView gangText;

        public StudentViewHolder(View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.tvGroupTitle);
            gangText = itemView.findViewById(R.id.tvGroupGangFullName);
        }
    }
}
