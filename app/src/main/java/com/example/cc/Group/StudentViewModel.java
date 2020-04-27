package com.example.cc.Group;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cc.database.NameDataBase;
import com.example.cc.database.Student;
import com.example.cc.database.StudentDao;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private StudentDao studentDao;
    private LiveData<List<Student>> studentLiveData;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentDao = NameDataBase.getNameDatabase(application).groupDao();
        studentLiveData = studentDao.getAllStudents();
    }

    public LiveData<List<Student>> getGroupList() {
        return studentLiveData;
    }

    public void insert(Student... students) {
        studentDao.insert(students);
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public void deleteAll() {
        studentDao.deleteAll();
    }
}
