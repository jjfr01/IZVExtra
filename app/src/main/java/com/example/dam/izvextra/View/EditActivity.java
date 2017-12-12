package com.example.dam.izvextra.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
    private TextInputEditText tietPlace;
    private EditText etDescription;
    private ImageView imgDate;
    private ArrayList<Group> grps = new ArrayList<>();
    private ArrayList<Teacher> tchs = new ArrayList<>();
    private ArrayList<CheckBox> cbG = new ArrayList<>();
    private ArrayList<CheckBox> cbT = new ArrayList<>();
    private Excursion exc;

    private int accion = 0;


    private void init() {

        lleditTeacher = findViewById(R.id.lleditTeacher);
        lleditGroup = findViewById(R.id.lleditGroup);
        etDescription = findViewById(R.id.etDescription);
        tietPlace = findViewById(R.id.tietPlace);
        imgDate = findViewById(R.id.imgDate);

        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialogDate();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();

        loadData();

        genereCBTeachers();

        genereCBGroups();

        if (isTablet(this)){

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        } else {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private void loadData(){

        grps = getIntent().getParcelableArrayListExtra("Groups");
        tchs = getIntent().getParcelableArrayListExtra("Teachers");
        exc = getIntent().getParcelableExtra("Excursion");
        accion = getIntent().getIntExtra("Accion", 0);

        tietPlace.setText(exc.getPlace());
        etDescription.setText(exc.getDescription());

    }

    private void genereCBTeachers() {

        if (accion == 1) {

            for (int i = 0; i < tchs.size(); i++) {

                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(tchs.get(i).getNameTeacher());
                cb.setTextSize(16);
                cb.setTextColor(this.getResources().getColor(R.color.colorBlack));
                if (checkTeacher(cb.getText().toString())) {

                    cb.setChecked(true);

                }

                lleditTeacher.addView(cb);
                cbT.add(cb);

            }

        } else {

            for (int i = 0; i < tchs.size(); i++) {

                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(tchs.get(i).getNameTeacher());
                cb.setTextSize(16);
                cb.setTextColor(this.getResources().getColor(R.color.colorBlack));
                lleditTeacher.addView(cb);
                cbT.add(cb);

            }

        }

    }

    private void genereCBGroups() {

        if (accion == 1) {

            for (int i = 0; i < grps.size(); i++) {

                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(grps.get(i).getNameGroup());
                cb.setTextSize(16);
                cb.setTextColor(this.getResources().getColor(R.color.colorBlack));
                if (checkGroup(cb.getText().toString())) {

                    cb.setChecked(true);

                }

                lleditGroup.addView(cb);
                cbG.add(cb);

            }


        } else {

            for (int i = 0; i < grps.size(); i++) {

                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(grps.get(i).getNameGroup());
                cb.setTextSize(16);
                cb.setTextColor(this.getResources().getColor(R.color.colorBlack));
                lleditGroup.addView(cb);
                cbG.add(cb);

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

    private void launchDialogDate() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater li = this.getLayoutInflater();
        final AlertDialog ad;

        alert.setView(li.inflate((R.layout.date_window), null));

        ad = alert.create();
        ad.show();

    }

}
