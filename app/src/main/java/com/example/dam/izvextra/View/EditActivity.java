package com.example.dam.izvextra.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;

import java.util.ArrayList;
import java.util.Arrays;

public class EditActivity extends AppCompatActivity {

    private LinearLayout lleditTeacher;
    private LinearLayout lleditGroup;
    private ArrayList<Group> grps = new ArrayList<>();
    private ArrayList<Teacher> tchs = new ArrayList<>();
    private Excursion exc;

    private int accion = 0;


    private void init() {

        lleditTeacher = findViewById(R.id.lleditTeacher);
        lleditGroup = findViewById(R.id.lleditGroup);

        grps = getIntent().getParcelableArrayListExtra("Groups");
        tchs = getIntent().getParcelableArrayListExtra("Teachers");
        exc = getIntent().getParcelableExtra("Excursion");
        accion = getIntent().getIntExtra("Accion", 0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();

        genereCBTeachers();

        genereCBGroups();

    }

    private void genereCBTeachers() {

        if (accion == 1) {

            for (int i = 0; i < tchs.size(); i++) {

                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(tchs.get(i).getNameTeacher());
                cb.setTextSize(16);
                if (checkTeacher(cb.getText().toString())) {

                    cb.setChecked(true);

                }

                lleditTeacher.addView(cb);

            }


        } else {

            for (int i = 0; i < tchs.size(); i++) {

                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(tchs.get(i).getNameTeacher());
                cb.setTextSize(16);
                lleditTeacher.addView(cb);

            }

        }


    }

    private void genereCBGroups() {

        if (accion == 1) {

            for (int i = 0; i < grps.size(); i++) {

                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(grps.get(i).getNameGroup());
                cb.setTextSize(16);
                if (checkGroup(cb.getText().toString())) {

                    cb.setChecked(true);

                }

                lleditGroup.addView(cb);

            }


        } else {

            for (int i = 0; i < grps.size(); i++) {

                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(grps.get(i).getNameGroup());
                cb.setTextSize(16);
                lleditGroup.addView(cb);

            }

        }


    }

    private boolean checkTeacher(String name) {

        boolean control = false;

        ArrayList<String> tchs = new ArrayList<>(Arrays.asList(exc.getTeachers().split(", ")));

        for (int i = 0; i < tchs.size(); i++) {

            if (tchs.get(i).equals(name)) {

                control = true;

            }

        }

        return control;
    }

    private boolean checkGroup(String name) {

        boolean control = false;

        ArrayList<String> grps = new ArrayList<>(Arrays.asList(exc.getGroups().split(", ")));

        for (int i = 0; i < grps.size(); i++) {

            if (grps.get(i).equals(name)) {

                control = true;

            }

        }

        return control;
    }

}
