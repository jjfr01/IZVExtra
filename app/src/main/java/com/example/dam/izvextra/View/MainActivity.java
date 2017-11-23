package com.example.dam.izvextra.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.Adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private void init(){

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerAdapter ra = new RecyclerAdapter(generarDatos(), this);
        rv.setAdapter(ra);

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private ArrayList<Excursion> generarDatos(){

        ArrayList<Excursion> excs = new ArrayList<>();

        for (int i = 0; i<2; i++){

            Teacher tch = new Teacher("Juanjo", "Fernandez", 1);
            ArrayList<Teacher> tchs = new ArrayList<>();
            tchs.add(tch);
            Group grp = new Group("A", 1);
            ArrayList<Group> grps = new ArrayList<>();
            grps.add(grp);
            Calendar calendar = Calendar.getInstance();
            Date date =  calendar.getTime();
            Excursion exc = new Excursion(tchs, grps, "Prueba", "Casa", "11:15", date);

            excs.add(exc);

        }

        return excs;
    }
}
