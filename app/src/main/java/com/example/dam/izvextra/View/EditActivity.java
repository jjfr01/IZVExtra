package com.example.dam.izvextra.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    private LinearLayout lleditTeacher;
    private LinearLayout lleditGroup;
    private TextInputEditText tietPlace;
    private EditText etDescription;
    private ImageView imgDate, imgHour;
    private TextView tvDate, tvHour;
    private Toolbar toolbarEdit;
    private ArrayList<Group> grps = new ArrayList<>();
    private ArrayList<Teacher> tchs = new ArrayList<>();
    private ArrayList<CheckBox> cbG = new ArrayList<>();
    private ArrayList<CheckBox> cbT = new ArrayList<>();
    private Excursion exc;

    private int accion = 0;


    private void init() {

        toolbarEdit = findViewById(R.id.toolbarEdit);

        setSupportActionBar(toolbarEdit);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        lleditTeacher = findViewById(R.id.lleditTeacher);
        lleditGroup = findViewById(R.id.lleditGroup);
        etDescription = findViewById(R.id.etDescription);
        tietPlace = findViewById(R.id.tietPlace);
        imgDate = findViewById(R.id.imgDate);
        imgHour = findViewById(R.id.imgHour);
        tvDate = findViewById(R.id.tvDate);
        tvHour = findViewById(R.id.tvHour);

        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialogDate();
            }
        });

        imgHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialogHour();
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

        if (isTablet(this)) {

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

    private void loadData() {

        grps = getIntent().getParcelableArrayListExtra("Groups");
        tchs = getIntent().getParcelableArrayListExtra("Teachers");
        exc = getIntent().getParcelableExtra("Excursion");
        accion = getIntent().getIntExtra("Accion", 0);

        tietPlace.setText(exc.getPlace());
        etDescription.setText(exc.getDescription());
        tvDate.setText(exc.getDate());
        tvHour.setText(exc.getHour());

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

        Button btnDate, btnDClose;
        final DatePicker datePicker;

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater li = this.getLayoutInflater();
        final AlertDialog ad;

        alert.setView(li.inflate((R.layout.date_window), null));

        ad = alert.create();
        ad.show();

        btnDate = ad.findViewById(R.id.btnDate);
        btnDClose = ad.findViewById(R.id.btnDClose);
        datePicker = ad.findViewById(R.id.datePicker);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvDate.setText(getDateFromDatePicker(datePicker));

                ad.cancel();
            }
        });

        btnDClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.cancel();
            }
        });

    }

    private String getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();


        return day + "-" + month + "-" + year;
    }

    private void launchDialogHour() {

        Button btnHour, btnHClose;
        final TimePicker timePicker;

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater li = this.getLayoutInflater();
        final AlertDialog ad;

        alert.setView(li.inflate((R.layout.hour_window), null));

        ad = alert.create();
        ad.show();

        btnHour = ad.findViewById(R.id.btnHour);
        btnHClose = ad.findViewById(R.id.btnHClose);
        timePicker = ad.findViewById(R.id.timePicker);

        btnHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvHour.setText(getHourFromTimePicker(timePicker));

                ad.cancel();
            }
        });

        btnHClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.cancel();
            }
        });

    }

    private String getHourFromTimePicker(TimePicker timePicker) {

        String minute = "";
        String hour = "";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            hour = String.valueOf(timePicker.getHour());
            minute = String.valueOf(timePicker.getMinute());

        } else {

            hour = String.valueOf(timePicker.getCurrentHour());
            minute = String.valueOf(timePicker.getCurrentMinute());

        }

        if (hour.length() == 1) {
            hour = "0" + hour;
        }

        if (minute.length() == 1) {
            minute = "0" + minute;
        }

        return hour + ":" + minute;
    }

}


