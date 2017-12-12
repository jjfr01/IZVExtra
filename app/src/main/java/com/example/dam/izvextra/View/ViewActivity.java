package com.example.dam.izvextra.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewActivity extends AppCompatActivity {

    private TextView tvPlace, tvDescripcion, tvGroup, tvTeacher, tvDate, tvHour;
    private Excursion exc;

    private void init() {

        tvPlace = findViewById(R.id.tvVPlace);
        tvDescripcion = findViewById(R.id.tvVDescription);
        tvGroup = findViewById(R.id.tvVGroup);
        tvTeacher = findViewById(R.id.tvVTeacher);
        tvDate = findViewById(R.id.tvVDate);
        tvHour = findViewById(R.id.tvVHour);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        init();

        exc = getIntent().getParcelableExtra("Excursion");

        loadData();

    }

    private void loadData() {

        ArrayList<String> grps = new ArrayList<>(Arrays.asList(exc.getGroups().split(", ")));

        ArrayList<String> tchs = new ArrayList<>(Arrays.asList(exc.getTeachers().split(", ")));

        tvPlace.setText(exc.getPlace());
        tvDescripcion.setText(exc.getDescription());
        tvGroup.setText(getStringtoArray(grps));
        tvTeacher.setText(getStringtoArray(tchs));
        tvDate.setText(exc.getDate());
        tvHour.setText(exc.getHour());

    }

    private String getStringtoArray(ArrayList<String> array) {

        String result = "";

        for (int i = 0; i < array.size(); i++) {

            if (i != array.size() - 1) {

                result += array.get(i) + "\n";

            } else {

                result += array.get(i);

            }

        }

        return result;
    }

}
