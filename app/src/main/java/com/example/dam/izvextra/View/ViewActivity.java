package com.example.dam.izvextra.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.R;

public class ViewActivity extends AppCompatActivity {

    private TextView tvPlace, tvDescripcion, tvGroup, tvTeacher, tvDate, tvHour;
    private Excursion exc;

    private void init(){

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

    private void loadData(){

        tvPlace.setText(exc.getPlace());
        tvDescripcion.setText(exc.getDescription());
        tvDate.setText(exc.getDate());
        tvHour.setText(exc.getHour());

    }

}
